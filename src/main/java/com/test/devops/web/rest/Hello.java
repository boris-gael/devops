package com.test.devops.web.rest;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class Hello {

    @GetMapping("")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from devops test app");
    }

}
