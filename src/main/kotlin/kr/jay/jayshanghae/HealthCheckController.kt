package kr.jay.jayshanghae

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/**
 * HealthCheckController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/11
 */

@RequestMapping("/health")
@RestController
class HealthCheckController {

    @Value("\${spring.profiles.active}")
    private lateinit var activeProfile: String

    @GetMapping
    fun healthCheck(): StatusResponse = StatusResponse(activeProfile)
}

data class StatusResponse(
    val activeProfile: String,
    val status: String,
    val timeStamp: LocalDateTime,
) {
    constructor(activeProfile: String) :
        this(activeProfile, "up", LocalDateTime.now())
}
