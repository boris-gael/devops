package com.test.devops.service;

import com.test.devops.exception.DevopsExeption;

import java.util.Map;

public interface AuthService {

    Map<String, String> authenticate(String basicAuth) throws DevopsExeption;

    Map<String, String> refreshToken(String refreshToken) throws DevopsExeption;
}
