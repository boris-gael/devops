package com.test.devops.config.batch;

import com.test.devops.service.dto.ProductDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductItemProcessor implements ItemProcessor<ProductDTO, ProductDTO> {

    @Override
    public ProductDTO process(ProductDTO productDTO) throws Exception {
        return productDTO;
    }
}
