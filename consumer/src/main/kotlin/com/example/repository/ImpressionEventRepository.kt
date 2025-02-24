package com.example.repository

import com.example.entity.kafka.ImpressionEvent
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface ImpressionEventRepository : CassandraRepository<ImpressionEvent, String> {
}