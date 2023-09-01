package com.quid.wms.inbound

import com.quid.wms.annotation.ApiTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

@ApiTest
class ConfirmInboundApiTest {

    @Test
    @DisplayName("입고 승인 api")
    fun registInbound() {
        val request = 1L

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/api/inbounds/$request/confirm")
            .then()
            .log().all()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }
}