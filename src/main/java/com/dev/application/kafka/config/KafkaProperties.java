package com.dev.application.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProperties {
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private  String bootstrapServers;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private  String autoOffsetReset;

    @Value("${spring.kafka.consumer.key-deserialize}")
    private  String keyDeserialize;





    @Value("${spring.kafka.consumer.value-deserialize}")
    private  String valueDeserialize;




    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public String getKeyDeserialize() {
        return keyDeserialize;
    }

    public String getValueDeserialize() {
        return valueDeserialize;
    }


}