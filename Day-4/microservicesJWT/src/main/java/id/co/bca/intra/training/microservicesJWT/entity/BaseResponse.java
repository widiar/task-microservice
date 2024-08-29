package id.co.bca.intra.training.microservicesJWT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BaseResponse<T> {
    private String message = "Success";
    private T data;

    @JsonIgnore
    private int httpCode = 200;
}
