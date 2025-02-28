package com.example.listener

import com.example.entity.cassandra.AdEvent
import com.example.entity.kafka.ClickEvent
import com.example.entity.kafka.ImpressionEvent
import com.example.mapper.AdEventMapper
import com.example.repository.AdEventRepository
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
@KafkaListener(id = "my-consumer-group", topics = ["events_topic"])
open class MultiTypeKafkaListener(
    val adEventRepository: AdEventRepository,
    val adEventMapper: AdEventMapper
    ) {
    @KafkaHandler
    fun handleImpressionEvent(impressionEvent: ImpressionEvent) : AdEvent? {
        var adEvent = adEventMapper.impressionEventToAdEvent(impressionEvent)
        adEvent = adEventRepository.save(adEvent)
        println("AdEvent inserted successfully: $adEvent")
        return adEvent
    }

    @KafkaHandler
    fun handleClickEvent(clickEvent: ClickEvent) : AdEvent? {
        var adEvent = adEventRepository.findByRequestId(clickEvent.requestId)
        adEvent?.apply {
            adEventMapper.clickEventToAdEvent(clickEvent, adEvent!!)
            adEvent = adEventRepository.save(adEvent)
            println("AdEvent updated: $adEvent")
        } ?: run {
            println("AdEvent ${clickEvent.requestId} not found")
        }
        return adEvent
    }

    @KafkaHandler(isDefault = true)
    fun handleUnknown(object1: Any) : AdEvent? {
        println("Unknown type received: $object1")
        return null;
    }
}