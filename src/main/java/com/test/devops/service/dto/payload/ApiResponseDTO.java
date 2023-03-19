package com.test.devops.service.dto.payload;

import com.test.devops.exception.DevopsExeption;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class ApiResponseDTO {

    @NonNull
    private String message;

    private Throwable cause;

    public ApiResponseDTO(DevopsExeption devopsExeption) {
        message = devopsExeption.getMessage();
        cause = devopsExeption.getCause();
    }

}
