package id.co.bca.intra.training.microservicesJWT.controller;

import id.co.bca.intra.training.microservicesJWT.entity.BaseResponse;
import id.co.bca.intra.training.microservicesJWT.entity.UserDto;
import id.co.bca.intra.training.microservicesJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public ResponseEntity<BaseResponse<List<UserDto>>> getAll(){
        return new ResponseEntity<>(userService.fetchAll(), HttpStatus.OK);
    }
}
