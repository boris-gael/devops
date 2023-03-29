package com.test.devops.config.batch;

import com.test.devops.config.ProductBatchProperties;
import com.test.devops.service.dto.ProductDTO;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ProductItemReader extends FlatFileItemReader<ProductDTO> {
    private final static String FILE_DELIMITER = ";";
    private final static int LINES_TO_SKIP = 1;
    private final static String STEP_ONE_ITEM_READER_NAME = "step1ItemReader";

    public ProductItemReader(ProductBatchProperties productBatchProperties) {
        super();
        this.setName(STEP_ONE_ITEM_READER_NAME);
        this.setLinesToSkip(LINES_TO_SKIP);
        this.setResource(new ClassPathResource(productBatchProperties.getFilePath()));
        this.setLineMapper(getProductDTOLineMapper());
    }

    private LineMapper<ProductDTO> getProductDTOLineMapper() {
        DefaultLineMapper<ProductDTO> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(FILE_DELIMITER);
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames("name", "description", "price");
        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        BeanWrapperFieldSetMapper<ProductDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ProductDTO.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
