package com.example.config

import com.example.entity.kafka.ClickEvent
import com.example.entity.kafka.ImpressionEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
open class KafkaConsumerConfig {
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String;

    @Bean
    open fun multiTypeConverter(): RecordMessageConverter {
        val converter = StringJsonMessageConverter()
        val typeMapper = DefaultJackson2JavaTypeMapper()
        typeMapper.typePrecedence = Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID
        typeMapper.addTrustedPackages("*")
        val mappings: MutableMap<String, Class<*>> = HashMap()
        mappings["clickEvent"] = ClickEvent::class.java
        mappings["impressionEvent"] = ImpressionEvent::class.java
        typeMapper.idClassMapping = mappings
        converter.typeMapper = typeMapper
        return converter
    }

    @Bean
    open fun multiTypeConsumerFactory(): ConsumerFactory<String, Any> {
        val props = HashMap<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
//        val deserializer = JsonDeserializer<Any>()
//        deserializer.addTrustedPackages("*")
        return DefaultKafkaConsumerFactory(props);
    }

    @Bean
    open fun multiTypeKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = multiTypeConsumerFactory()
        factory.setRecordMessageConverter(multiTypeConverter())
        return factory
    }
}