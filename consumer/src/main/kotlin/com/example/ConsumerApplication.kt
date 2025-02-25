package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class ConsumerApplication

fun main(args: Array<String>) {
    println("Starting ConsumerApplication ...")
    runApplication<ConsumerApplication>(*args);
}