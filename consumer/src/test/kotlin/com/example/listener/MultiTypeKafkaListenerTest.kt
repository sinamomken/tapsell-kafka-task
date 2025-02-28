package com.example.listener

import com.example.entity.cassandra.AdEvent
import com.example.entity.kafka.ClickEvent
import com.example.entity.kafka.ImpressionEvent
import com.example.mapper.AdEventMapper
import com.example.repository.AdEventRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mapstruct.factory.Mappers
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class MultiTypeKafkaListenerTest {

    @Mock
    private lateinit var adEventRepository: AdEventRepository

    private val adEventMapper = Mappers.getMapper(AdEventMapper::class.java)

    val impressionEvent1 = ImpressionEvent(
        requestId = "1",
        impressionTime = 1000L,
        adId = "Ad1",
        appId = "App1",
        advertiserCost = 10.0,
        appTitle = "App Title 1",
        adTitle = "Advertisement Title 1"
    )

    val adEvent1 = AdEvent(
        requestId = "1",
        impressionTime = 1000L,
        adId = "Ad1",
        appId = "App1",
        advertiserCost = 10.0,
        appTitle = "App Title 1",
        adTitle = "Advertisement Title 1",
        clickTime = 0
    )

    val clickEvent1 = ClickEvent(
        requestId = "1",
        clickTime = 1
    )

    val adEvent2 = AdEvent(
        requestId = "1",
        impressionTime = 1000L,
        adId = "Ad1",
        appId = "App1",
        advertiserCost = 10.0,
        appTitle = "App Title 1",
        adTitle = "Advertisement Title 1",
        clickTime = 1
    )

    @Test
    fun whenGetImpressionEvent_ReturnNewAdEvent() {
        // Given
        whenever(adEventRepository.save(adEvent1)).thenReturn(adEvent1)

        // When
        val listener = MultiTypeKafkaListener(adEventRepository, adEventMapper)
        val result = listener.handleImpressionEvent(impressionEvent1)

        // Then
        assertEquals(result, adEvent1)
    }

    @Test
    fun whenGetClickEvent_UpdateAdEvent() {
        // Given
        whenever(adEventRepository.findByRequestId("1")).thenReturn(adEvent1)
        whenever(adEventRepository.save(adEvent2)).thenReturn(adEvent2)

        // When
        val listener = MultiTypeKafkaListener(adEventRepository, adEventMapper)
        val result = listener.handleClickEvent(clickEvent1)

        // Then
        assertEquals(result, adEvent2)
    }
}