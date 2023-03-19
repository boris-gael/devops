package com.test.devops.web.rest;

import com.test.devops.aop.annotation.ApiUrl;
import com.test.devops.exception.DevopsExeption;
import com.test.devops.service.ProductService;
import com.test.devops.service.dto.ProductDTO;
import com.test.devops.service.dto.payload.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class ProductResource {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final ProductService productService;
    private final KafkaTemplate kafkaTemplate;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.ok(productService.createProduct(productDTO));
        } catch (DevopsExeption ex) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO(ex));
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(productService.findAll());
        } catch (DevopsExeption ex) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO(ex));
        }
    }

    @GetMapping("/{id}")
    @ApiUrl("/api/products/{id}")
    public ResponseEntity<Object> getOneById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (DevopsExeption ex) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO(ex));
        }
    }

    @PostMapping("publish")
    public ResponseEntity<String> publish(@RequestParam(defaultValue = "Keyboard!") String message) {
        ListenableFuture<String> future = kafkaTemplate.send(topicName, message);
        return ResponseEntity.ok(future.isDone() ? "ok" : "nok");
    }

    @PostMapping("/batch/run")
    public ResponseEntity<Object> runBatch() {
        try {
            return ResponseEntity.ok(productService.loadProductsBatch());
        } catch (DevopsExeption ex) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO(ex));
        }
    }

}
