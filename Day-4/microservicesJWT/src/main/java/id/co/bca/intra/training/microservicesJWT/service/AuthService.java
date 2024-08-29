package id.co.bca.intra.training.microservicesJWT.service;

import id.co.bca.intra.training.microservicesJWT.entity.BaseResponse;
import id.co.bca.intra.training.microservicesJWT.entity.User;
import id.co.bca.intra.training.microservicesJWT.entity.UserDto;
import id.co.bca.intra.training.microservicesJWT.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    public BaseResponse<UserDto> register(User newUser){
        BaseResponse<UserDto> result = new BaseResponse<>();
        int httpCode = 201;

        UserDto dto = new UserDto();
        dto.setUsername(newUser.getUsername());
        result.setData(dto);

        Optional<User> user = userRepository.findByUsername(newUser.getUsername());
        if (user.isPresent() || StringUtils.isEmpty(newUser.getPassword())) {
            httpCode = 400;
            if (user.isPresent()) result.setMessage("User already exists");
            else result.setMessage("Password can't empty");
            result.setData(null);
        }

        result.setHttpCode(httpCode);
        if (httpCode == HttpStatus.CREATED.value()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(newUser.getPassword());
            newUser.setPassword(password);
            userRepository.save(newUser);
        }
        return result;
    }

    public BaseResponse<UserDto> login(User user){
        BaseResponse<UserDto> result = new BaseResponse<>();
        UserDto out = new UserDto();
        int httpCode = 200;

        User userExist = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (userExist == null) {
            result.setMessage("User not found");
            httpCode = 404;
        }

        result.setHttpCode(httpCode);
        if (httpCode == 200) {
            out.setUsername(user.getUsername());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(user.getPassword(), userExist.getPassword())){
                String jwt = jwtService.generateToken(user.getUsername());
                out.setToken(jwt);
            } else result.setMessage("Wrong password");
            result.setData(out);
        }

        return result;
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
