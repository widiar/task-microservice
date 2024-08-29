package id.co.bca.intra.training.microservicesJWT.utils;

import id.co.bca.intra.training.microservicesJWT.entity.User;
import id.co.bca.intra.training.microservicesJWT.service.JwtService;
import id.co.bca.intra.training.microservicesJWT.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        try{
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7); // Extract token
                username = jwtService.extractUsername(token); // Extract username from token
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                User user = authService.loadUserByUsername(username);
                if (jwtService.validateToken(token, user)){
                    UsernamePasswordAuthenticationToken autToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            null
                    );
                    autToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(autToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            LogUtils.logErrorResponse(request, logger, response);
        }
    }
}
