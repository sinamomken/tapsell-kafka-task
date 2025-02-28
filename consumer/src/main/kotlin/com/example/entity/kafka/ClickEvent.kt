package com.example.entity.kafka

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ClickEvent @JsonCreator constructor(
    @JsonProperty("requestId") val requestId: String,
    @JsonProperty("clickTime") val clickTime: Long)