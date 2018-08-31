package com.github.davinkevin.springbootgcp.demotracing

import org.apache.commons.logging.LogFactory
import org.springframework.cloud.sleuth.annotation.NewSpan
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WorkService(val webClient: WebClient) {

    @NewSpan
    fun busyWork(): Mono<Void> {
        LOGGER.info("starting busy work")
        return Mono
                .`when`((0..2).map { webClient.get().uri("meet").exchange() })
                .doFinally { LOGGER.info("finished busy work") }
    }

    companion object {
        private val LOGGER = LogFactory.getLog(WorkService::class.java)
    }
}