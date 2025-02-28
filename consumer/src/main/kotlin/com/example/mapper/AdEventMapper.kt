package com.example.mapper

import com.example.entity.cassandra.AdEvent
import com.example.entity.kafka.ClickEvent
import com.example.entity.kafka.ImpressionEvent
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
interface AdEventMapper {

    fun impressionEventToAdEvent(impressionEvent: ImpressionEvent): AdEvent

    @Mapping(source = "clickTime", target = "clickTime")
    fun clickEventToAdEvent(clickEvent: ClickEvent, @MappingTarget adEvent: AdEvent)
}