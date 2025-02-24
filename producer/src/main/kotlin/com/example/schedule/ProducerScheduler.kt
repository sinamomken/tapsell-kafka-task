package com.example.schedule

import com.example.entity.kafka.ClickEvent
import com.example.entity.kafka.ImpressionEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
open class ProducerScheduler {
    companion object {
        private val AD_ID = "Ad1";
        private val AD_TITLE = "Advertisement Title 1";
        private val ADVERTISER_COST = 10.0;
        private val APP_ID = "App1";
        private val APP_TITLE = "App Title 1";
        private val IMPRESSION_TIME = 1000L;
    }

//    @Autowired
//    private lateinit var kafkaTemplate: KafkaTemplate<String, Any>;

    private var requestId = 1;
    private var clickTime = 1L;

    @Scheduled(fixedDelay = 1000)
    fun produce() {
        println("Start of produce()")
        /*val impressionEvent = ImpressionEvent(
            requestId = this.requestId.toString(),
            adId = AD_ID,
            adTitle = AD_TITLE,
            advertiserCost = ADVERTISER_COST,
            appId = APP_ID,
            appTitle = APP_TITLE,
            impressionTime = IMPRESSION_TIME
            )
        val impressionEventFuture = kafkaTemplate.send("events_topic", impressionEvent)
        impressionEventFuture.whenComplete{ result, ex ->
            run {
                if (ex == null) {
                    println(
                        "Sent impressionEvent=[" + impressionEvent.requestId +
                                "] with offset=[" + result.recordMetadata.offset() + "]"
                    )
                } else {
                    println(
                        "Unable to send impressionEvent=[" + impressionEvent.requestId +
                                "] due to : " + ex.message
                    )
                }
            }
        }
        val clickEvent = ClickEvent(
            requestId = this.requestId.toString(),
            clickTime = this.clickTime
        )
        val clickEventFuture = kafkaTemplate.send("events_topic", clickEvent)
        clickEventFuture.whenComplete{ result, ex ->
            run {
                if (ex == null) {
                    println(
                        "Sent clickEvent=[" + clickEvent.requestId +
                                "] with offset=[" + result.recordMetadata.offset() + "]"
                    )
                } else {
                    println(
                        "Unable to send clickEvent=[" + clickEvent.requestId +
                                "] due to : " + ex.message
                    )
                }
            }

        }*/

        requestId++
        clickTime++
    }
}