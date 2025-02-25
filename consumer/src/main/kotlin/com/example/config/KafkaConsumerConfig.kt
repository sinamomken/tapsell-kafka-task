package com.example.config

import com.example.entity.kafka.ClickEvent
import com.example.entity.kafka.ImpressionEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper


@Configuration
open class KafkaConsumerConfig {
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String;

    @Bean
    open fun multiTypeConverter(): RecordMessageConverter {
        val converter = StringJsonMessageConverter()
        val typeMapper = DefaultJackson2JavaTypeMapper()
        typeMapper.typePrecedence = Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID
        typeMapper.addTrustedPackages("com.example.entity.kafka")
        val mappings: MutableMap<String, Class<*>> = HashMap()
        mappings["clickEvent"] = ClickEvent::class.java
        mappings["impressionEvent"] = ImpressionEvent::class.java
        typeMapper.idClassMapping = mappings
        converter.typeMapper = typeMapper
        return converter
    }


}