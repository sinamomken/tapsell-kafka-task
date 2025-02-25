package com.example.listener

import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
@KafkaListener(id = "my-consumer-group", topics = ["events_topic"])
open class MultiTypeKafkaListener {
    @KafkaHandler(isDefault = true)
    fun handleUnknown(object1: Any) {
        println("Unknown type received: $object1")
    }
}