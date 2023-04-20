package com.test.devops.service.mapper;

import com.test.devops.domain.Product;
import com.test.devops.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "categoryDTO", target = "category")
    })
    Product toEntity(ProductDTO productDTO);

    @Mappings({
            @Mapping(source = "category", target = "categoryDTO")
    })
    ProductDTO toDto(Product product);

    List<Product> toEntity(List<ProductDTO> productDTOS);

    List<ProductDTO> toDto(List<Product> products);

}
