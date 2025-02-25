package com.example.entity.kafka

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table


data class ImpressionEvent @JsonCreator constructor(
    @JsonProperty("requestId") val requestId: String, // a unique id for the request
    @JsonProperty("adId") val adId: String,
    @JsonProperty("adTitle") val adTitle: String,
    @JsonProperty("advertiserCost") val advertiserCost: Double,
    @JsonProperty("appId") val appId: String,
    @JsonProperty("appTitle") val appTitle: String,
    @JsonProperty("impressionTime") val impressionTime: Long)