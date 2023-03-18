package com.test.devops.domain.payload;

import com.test.devops.exception.DevopsExeption;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class ApiResponse {

    @NonNull
    private String message;

    private Throwable cause;

    public ApiResponse(DevopsExeption devopsExeption) {
        message = devopsExeption.getMessage();
        cause = devopsExeption.getCause();
    }

}
