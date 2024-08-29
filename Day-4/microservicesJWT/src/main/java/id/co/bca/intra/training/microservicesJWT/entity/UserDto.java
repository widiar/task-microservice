package id.co.bca.intra.training.microservicesJWT.entity;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String token;
}
