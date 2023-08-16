package com.test.devops.service.impl;

import com.test.devops.domain.Product;
import com.test.devops.repository.ProductRepository;
import com.test.devops.service.dto.ProductDTO;
import com.test.devops.service.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Test
    void createProductTest() {
        Mockito.when(productMapper.toDto(Mockito.any(Product.class))).thenReturn(new ProductDTO());
        Mockito.when(productMapper.toEntity(Mockito.any(ProductDTO.class))).thenReturn(new Product());
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(new Product());

        ProductDTO productDTO = productService.createProduct(new ProductDTO());

        Assertions.assertAll(
                () -> assertThat(productDTO).isNotNull()
        );

        Mockito.verify(productMapper).toDto(Mockito.any(Product.class));
        Mockito.verify(productMapper).toEntity(Mockito.any(ProductDTO.class));
        Mockito.verify(productRepository).save(Mockito.any(Product.class));
    }

}
