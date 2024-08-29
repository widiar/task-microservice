package id.co.bca.intra.training.microservicesJWT.service;

import id.co.bca.intra.training.microservicesJWT.entity.BaseResponse;
import id.co.bca.intra.training.microservicesJWT.entity.User;
import id.co.bca.intra.training.microservicesJWT.entity.UserDto;
import id.co.bca.intra.training.microservicesJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public BaseResponse<List<UserDto>> fetchAll(){
        BaseResponse<List<UserDto>> baseResponse = new BaseResponse<>();
        Iterable<User> users = userRepository.findAll();
        List<UserDto> data = new ArrayList<>();

        for(User user : users){
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            data.add(userDto);
        }
        baseResponse.setData(data);
        return baseResponse;
    }
}
