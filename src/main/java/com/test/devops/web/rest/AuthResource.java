package com.test.devops.web.rest;

import com.test.devops.exception.DevopsExeption;
import com.test.devops.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class AuthResource {

    private final AuthService authService;

    @GetMapping("token")
    public ResponseEntity<Map<String, String>> getToken(
            @RequestHeader("Authorization") String basicAuth
    ) {
        try {
            return ResponseEntity.ok(authService.authenticate(basicAuth));
        } catch (DevopsExeption ex) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", ex.getMessage()));
        }
    }

    @GetMapping("refreshToken")
    public ResponseEntity<Map<String, String>> getNewToken(@RequestHeader("Authorization") String refreshToken) {
        try {
            return ResponseEntity.ok(authService.refreshToken(refreshToken));
        } catch (DevopsExeption ex) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", ex.getMessage()));
        }
    }
}
