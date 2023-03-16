package com.test.devops.web.rest;

import com.test.devops.service.ProductService;
import com.test.devops.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAll() {
        log.info("Get list of products");
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("publish")
    public ResponseEntity<String> publish(@RequestParam(defaultValue = "Keyboard!") String message) {
        CompletableFuture<String> future = kafkaTemplate.send(topicName, message);
        return ResponseEntity.ok(future.isDone() ? "ok" : "nok");
    }

}
