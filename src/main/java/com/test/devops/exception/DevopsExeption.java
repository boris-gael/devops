package com.test.devops.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DevopsExeption extends RuntimeException {

    public DevopsExeption(String message) {
        super(message);
    }

    public DevopsExeption(String message, Throwable cause) {
        super(message, cause);
    }

}
