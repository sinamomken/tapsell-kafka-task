package com.example.repository

import com.example.entity.cassandra.AdEvent
import com.example.entity.kafka.ImpressionEvent
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AdEventRepository : CassandraRepository<AdEvent, String> {
    fun findByRequestId(requestId: String): AdEvent?
}