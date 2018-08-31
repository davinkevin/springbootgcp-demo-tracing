package com.github.davinkevin.springbootgcp.demotracing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class DemoTracingApplication {
    @Bean fun webClient() = WebClient.builder().baseUrl("http://localhost:8080/").build()
}

fun main(args: Array<String>) {
    runApplication<DemoTracingApplication>(*args)
}

