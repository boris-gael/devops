package com.test.devops.config.kafka;

import com.test.devops.service.ProductService;
import com.test.devops.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class Listeners {

    @Autowired
    private ProductService productService;

    @KafkaListener(
            topics = "stringTopic"
    )
    void listener(String data, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("############################## Listener received " + data + " from partition: " + partition);
        productService.createProduct(ProductDTO.builder().name(data).description("").price(Double.valueOf(data.length())).build());
    }

}
