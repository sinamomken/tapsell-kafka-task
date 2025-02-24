package com.example.entity.kafka

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("impression_event")
data class ImpressionEvent (
    @PrimaryKey val requestId: String, // a unique id for the request
    val adId: String,
    val adTitle: String,
    val advertiserCost: Double,
    val appId: String,
    val appTitle: String,
    val impressionTime: Long)