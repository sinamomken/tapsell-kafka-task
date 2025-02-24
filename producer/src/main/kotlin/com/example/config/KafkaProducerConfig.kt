package com.example.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
open class KafkaProducerConfig {
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String;
    @Bean
    open fun multiTypeProducerFactory(): ProducerFactory<String, Any> {
        val configProps = HashMap<String, Any>();
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress;
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        configProps[JsonSerializer.TYPE_MAPPINGS] = "clickEvent:com.example.entity.kafka.ClickEvent, impressionEvent:com.example.entity.kafka.ImpressionEvent"
        return DefaultKafkaProducerFactory(configProps);
    }

    @Bean
    open fun multiTypeKafkaTemplate() : KafkaTemplate<String, Any> {
        return KafkaTemplate(multiTypeProducerFactory());
    }
}