package id.co.bca.intra.traning.microservicesRest.controller;

import id.co.bca.intra.traning.microservicesRest.entity.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private HashMap<String, UserDto> data = new HashMap<>();

    @GetMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto userDto(@PathVariable(name = "user-id") String userId){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        UserDto user = data.get(userId);
//        return user;

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto userDto(
            @RequestBody UserDto newUserDto
    ){
        UserDto user = newUserDto;
        data.put(user.getUserId(), user);
        return user;
    }
}
