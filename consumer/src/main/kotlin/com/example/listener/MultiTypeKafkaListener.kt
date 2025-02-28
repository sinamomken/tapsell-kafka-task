package com.example.listener

import com.example.entity.kafka.ClickEvent
import com.example.entity.kafka.ImpressionEvent
import com.example.mapper.AdEventMapper
import com.example.repository.ImpressionEventRepository
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
@KafkaListener(id = "my-consumer-group", topics = ["events_topic"])
open class MultiTypeKafkaListener(
    val impressionEventRepository: ImpressionEventRepository,
    val adEventMapper: AdEventMapper
    ) {
    @KafkaHandler
    fun handleClickEvent(clickEvent: ClickEvent) {
        println("ClickEvent received: $clickEvent")
    }

    @KafkaHandler
    fun handleImpressionEvent(impressionEvent: ImpressionEvent) {
        println("ImpressionEvent received: $impressionEvent")

    }

    @KafkaHandler(isDefault = true)
    fun handleUnknown(object1: Any) {
        println("Unknown type received: $object1")
    }
}