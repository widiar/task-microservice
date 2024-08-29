package id.co.bca.intra.training.microservicesJWT.controller;

import id.co.bca.intra.training.microservicesJWT.entity.BaseResponse;
import id.co.bca.intra.training.microservicesJWT.entity.UserDto;
import id.co.bca.intra.training.microservicesJWT.service.UserService;
import id.co.bca.intra.training.microservicesJWT.utils.LogUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("")
    public ResponseEntity<BaseResponse<List<UserDto>>> getAll(HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        LogUtils.logInfoRequest(request, logger);

        BaseResponse<List<UserDto>> output = userService.fetchAll();

        long endTime = System.currentTimeMillis();
        LogUtils.logInfoResponse(request, logger, output, endTime-startTime);
        return new ResponseEntity<>(output, HttpStatusCode.valueOf(output.getHttpCode()));
    }
}
