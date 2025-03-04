package com.example.entity.cassandra

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("adevent")
data class AdEvent(
    @PrimaryKey val requestId: String,
    val adId: String,
    val adTitle: String,
    val advertiserCost: Double,
    val appId: String,
    val appTitle: String,
    val impressionTime: Long,
    var clickTime: Long)