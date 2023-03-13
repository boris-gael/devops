package com.test.devops.service;

import com.test.devops.service.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> findAll();

}
