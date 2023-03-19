package com.test.devops.service.mapper;

import com.test.devops.domain.Product;
import com.test.devops.service.dto.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDTO productDTO);

    ProductDTO toDto(Product product);

    List<Product> toEntity(List<ProductDTO> productDTOS);

    List<ProductDTO> toDto(List<Product> products);

}
