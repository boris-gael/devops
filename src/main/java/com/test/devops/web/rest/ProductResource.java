package com.test.devops.web.rest;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.test.devops.aop.annotation.ApiUrl;
import com.test.devops.exception.DevopsExeption;
import com.test.devops.service.ProductService;
import com.test.devops.service.dto.ProductDTO;
import com.test.devops.service.dto.payload.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
    public ResponseEntity<Object> save(@RequestBody ProductDTO productDTO, Authentication auth) {
        log.info("Username and authorities: {}, {}", auth.getName(), auth.getAuthorities());
        try {
            return ResponseEntity.ok(productService.createProduct(productDTO));
        } catch (DevopsExeption ex) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO(ex));
        }
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<?> getAll(Authentication authentication) {
        try {
            HazelcastInstance hazelcastInstance = Hazelcast.getAllHazelcastInstances()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new DevopsExeption("No Hazelcast instance found."));

            log.info("distributed obj size: {}", hazelcastInstance.getDistributedObjects().size());
            List<ProductDTO> productDTOS = productService.findAll();
            log.info("productDTOS size: {}", productDTOS.size());
            return ResponseEntity.ok(productDTOS);
        } catch (DevopsExeption ex) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO(ex));
        }
    }

    @GetMapping("/{id}")
    @ApiUrl("/api/products/{1}")
    @ApiUrl("/api/products/{2}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
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

}
