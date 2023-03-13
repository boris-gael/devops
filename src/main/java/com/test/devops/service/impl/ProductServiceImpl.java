package com.test.devops.service.impl;

import com.test.devops.repository.ProductRepository;
import com.test.devops.service.ProductService;
import com.test.devops.service.dto.ProductDTO;
import com.test.devops.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDTO)));
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

}
