package id.co.bca.intra.training.microservicesJWT.utils;

import id.co.bca.intra.training.microservicesJWT.entity.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LogUtils {

    public static void logInfoRequest(HttpServletRequest request, Logger log){
        String logMsg = String.format("ON REQUEST  --- URL: %s | Method: %s",
                request.getRequestURI(),
                request.getMethod());
        log.info(logMsg);
    }

    public static <T> void logInfoResponse(HttpServletRequest request, Logger log, BaseResponse<T> response, long time){
        String logMsg = String.format("ON RESPONSE --- URL: %s | Method: %s | Status Code: %d | Message: %s | Time: %dms",
                request.getRequestURI(),
                request.getMethod(),
                response.getHttpCode(),
                response.getMessage(),
                time);
        if (response.getHttpCode() == 200 || response.getHttpCode() == 201) log.info(logMsg);
        else log.error(logMsg);
    }

    public static void logErrorResponse(HttpServletRequest request, Logger log, HttpServletResponse response){
        String logMsg = String.format("ON RESPONSE --- URL: %s | Method: %s | Status Code: %d | Message: %s",
                request.getRequestURI(),
                request.getMethod(),
                HttpStatus.FORBIDDEN.value(),
                "FORBIDDEN ACCESS");
        log.error(logMsg);
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
