package com.github.davinkevin.springbootgcp.demotracing

import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.Duration

@RestController
class ExampleController(val workService: WorkService) {

    @RequestMapping("/")
    fun work(): Mono<String> {
        return workService
                .busyWork()
                .map { "finished" }
    }

    @RequestMapping("/meet")
    fun meet(): Mono<String> {
        val duration = 200L + (Math.random() * 800L).toLong()
        return Mono.just("meeting finished in " + duration + "ms")
                .doOnEach { LOGGER.info("meeting took " + duration + "ms") }
                .delayElement(Duration.ofMillis(duration))
    }

    companion object {
        private val LOGGER = LogFactory.getLog(ExampleController::class.java)
    }
}