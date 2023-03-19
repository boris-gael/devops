package com.test.devops.config.batch;

import com.test.devops.domain.Product;
import com.test.devops.repository.ProductRepository;
import com.test.devops.service.dto.ProductDTO;
import com.test.devops.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductItemWriter implements ItemWriter<ProductDTO> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void write(List<? extends ProductDTO> productDTOS) throws Exception {
        List<Product> products = productMapper.toEntity((List<ProductDTO>) productDTOS);
        productRepository.saveAll(products);
    }

}
