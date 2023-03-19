package com.test.devops.service;

import com.test.devops.exception.DevopsExeption;
import com.test.devops.service.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO) throws DevopsExeption;

    List<ProductDTO> findAll() throws DevopsExeption;

    ProductDTO findById(Long id) throws DevopsExeption;

    List<ProductDTO> saveAll(List<ProductDTO> productDTOS) throws DevopsExeption;

    String loadProductsBatch() throws DevopsExeption;
}
