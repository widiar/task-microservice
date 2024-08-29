package id.co.bca.intra.training.microservicesJWT.controller;


import id.co.bca.intra.training.microservicesJWT.entity.BaseResponse;
import id.co.bca.intra.training.microservicesJWT.entity.User;
import id.co.bca.intra.training.microservicesJWT.entity.UserDto;
import id.co.bca.intra.training.microservicesJWT.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserDto>> register(@RequestBody User newUser){
        BaseResponse<UserDto> output = authService.register(newUser);
        return new ResponseEntity<>(output, HttpStatusCode.valueOf(output.getHttpCode()));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<UserDto>> login(@RequestBody User user){
        BaseResponse<UserDto> output = authService.login(user);
        return new ResponseEntity<>(output, HttpStatusCode.valueOf(output.getHttpCode()));
    }
}
