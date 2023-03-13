package com.test.devops.web.rest;

import com.test.devops.service.ProductService;
import com.test.devops.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class ProductResource {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAll() {
        log.info("Get list of products");
        return ResponseEntity.ok(productService.findAll());
    }

}
