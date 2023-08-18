package com.test.devops.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloResource {

    @GetMapping("")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("HelloResource from devops test app");
    }

    @GetMapping("/someone")
    public ResponseEntity<String> helloSomeone(@RequestParam String name) {
        return ResponseEntity.ok("HelloResource " + name + ", how are you?");
    }

    @GetMapping("/goodbye")
    public ResponseEntity<String> goobye(@RequestParam String name) {
        return ResponseEntity.ok("Goodbye " + name + "! I'am going home.");
    }

}
