package com.test.devops.config.cdc;

import com.test.devops.service.ProductService;
import io.debezium.config.Configuration;
import io.debezium.embedded.EmbeddedEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@org.springframework.context.annotation.Configuration
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DebeziumConfig {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private EmbeddedEngine embeddedEngineBean;
    private final ProductService productService;

//    @ConditionalOnBean(EmbeddedEngine.class)
//    private void start(ApplicationContext context) {
//        log.info("Starting embedded bean thread...");
//        System.exit(0);
//        this.embeddedEngineBean = context.getBean(EmbeddedEngine.class);
//        executor.execute(embeddedEngineBean);
//    }

    @PreDestroy
    private void stop() {
        if (!Objects.isNull(this.embeddedEngineBean)) {
            this.embeddedEngineBean.stop();
        }
    }

    @Bean
    public EmbeddedEngine embeddedEngine(Configuration configuration, ProductService productService) {
        this.embeddedEngineBean = new EmbeddedEngine.BuilderImpl()
                .using(configuration)
                .notifying((record) -> {
                    log.info("record: {}", record);
                    productService.updateCacheProducts();
                })
                .build();
        this.executor.execute(this.embeddedEngineBean);
        return this.embeddedEngineBean;
    }

    @Bean
    public Configuration configuration() {
        Properties props = new Properties();
        props.setProperty("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        props.setProperty("offset.storage", "org.apache.kafka.connect.storage.MemoryOffsetBackingStore");
//        props.setProperty("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore");
//        props.setProperty("offset.storage.file.filename", "classpath:static//cdc//product-offset.dat");
        props.setProperty("offset.flush.interval.ms", "30000");
        props.setProperty("database.user", "root");
        props.setProperty("database.password", "");
        props.setProperty("database.hostname", "localhost");
        props.setProperty("database.port", "3306");
        props.setProperty("name", "productMyqlConnector");
        props.setProperty("database.server.name", "localhostDevops");
        props.setProperty("database.server.id", "154896");
        props.setProperty("database.server.dbname", "devops");
        props.setProperty("database.server.whitelist", "products");
        props.setProperty("topic.prefix", "cdc");
        props.setProperty("schema.history.internal.kafka.topic", "schemaHistoryTopic");
//        props.setProperty("schema.history.internal.kafka.bootstrap.servers", "localhost:9092,localhost:9093");
        return Configuration.from(props);
    }

}
