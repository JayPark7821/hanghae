package kr.jay.jayshanghae

import io.restassured.RestAssured
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

/**
 * HealthCheckControllerTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/09/11
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class HealthCheckControllerTest {

    @LocalServerPort
    val localServerPort: Int = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = localServerPort
    }

    @Test
    fun `health check`() {
        val response = RestAssured
            .given().log().all()
            .`when`()
            .get("/health")
            .then().log().all()
            .extract()

        val responseBody = response.body().jsonPath().getObject("", StatusResponse::class.java)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(responseBody.activeProfile).isEqualTo("test")
        assertThat(responseBody.status).isEqualTo("up")
        assertThat(responseBody.timeStamp).isBefore(LocalDateTime.now())
    }
}
