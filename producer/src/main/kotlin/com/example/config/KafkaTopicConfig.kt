package com.example.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin
import java.util.Objects

@Configuration
open class KafkaTopicConfig() {
    @Value(value = "\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String;

    @Bean
    open fun kafkaAdmin() : KafkaAdmin {
        val configs = HashMap<String, Any>();
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress;
        return KafkaAdmin(configs);
    }

    @Bean
    open fun topic1():NewTopic {
        return NewTopic("events_topic", 1, 1);
    }
}