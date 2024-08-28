package id.co.bca.intra.traning.microservicesRest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BaseResponse<T>{
    private String message = "Success";
    private T data;

    @JsonIgnore
    private int statusCode = 200;
}
