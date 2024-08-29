package id.co.bca.intra.training.microservicesJWT.controller;


import id.co.bca.intra.training.microservicesJWT.entity.BaseResponse;
import id.co.bca.intra.training.microservicesJWT.entity.User;
import id.co.bca.intra.training.microservicesJWT.entity.UserDto;
import id.co.bca.intra.training.microservicesJWT.service.AuthService;
import id.co.bca.intra.training.microservicesJWT.utils.LogUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
//import static net.logstash.logback.argument.StructuredArguments.keyValue;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserDto>> register(@RequestBody User newUser, HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        LogUtils.logInfoRequest(request, logger);

        BaseResponse<UserDto> output = authService.register(newUser);

        long endTime = System.currentTimeMillis();
        LogUtils.logInfoResponse(request, logger, output, endTime-startTime);
        return new ResponseEntity<>(output, HttpStatusCode.valueOf(output.getHttpCode()));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<UserDto>> login(@RequestBody User user, HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        LogUtils.logInfoRequest(request, logger);

        BaseResponse<UserDto> output = authService.login(user);

        long endTime = System.currentTimeMillis();
        LogUtils.logInfoResponse(request, logger, output, endTime-startTime);
        return new ResponseEntity<>(output, HttpStatusCode.valueOf(output.getHttpCode()));
    }
}
