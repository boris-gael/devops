package com.test.devops.service.impl;

import com.test.devops.domain.Product;
import com.test.devops.exception.DevopsExeption;
import com.test.devops.repository.ProductRepository;
import com.test.devops.service.ProductService;
import com.test.devops.service.dto.ProductDTO;
import com.test.devops.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final JobLauncher jobLauncher;
    private final Job job1;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws DevopsExeption {
        try {
            return productMapper.toDto(productRepository.save(productMapper.toEntity(productDTO)));
        } catch (Exception e) {
            throw new DevopsExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<ProductDTO> findAll() throws DevopsExeption {
        try {
            return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DevopsExeption(e.getMessage(), e.getCause());
        }
    }

    @Override
    public ProductDTO findById(Long id) throws DevopsExeption {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new DevopsExeption("No product found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> saveAll(List<ProductDTO> productDTOS) throws DevopsExeption {
        try {
            return productRepository.saveAll(productMapper.toEntity(productDTOS)).stream()
                    .map(productMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new DevopsExeption(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public String loadProductsBatch() throws DevopsExeption {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        try {
            JobExecution jobExecution = jobLauncher.run(job1, jobParameters);
            while (jobExecution.isRunning()) {
                log.info("job1 is still running ...");
            }
            return jobExecution.getStatus().name();
        } catch (Exception ex) {
            throw new DevopsExeption(ex.getMessage(), ex.getCause());
        }
    }

}
