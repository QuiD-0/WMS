package com.quid.wms.inbound

import com.quid.wms.annotation.ApiTest
import com.quid.wms.fixture.InboundFixture
import com.quid.wms.inbound.gateway.repository.InboundRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

@ApiTest
@Disabled
class ConfirmInboundApiTest {

    @Autowired
    private lateinit var inboundRepository: InboundRepository

    @Test
    @DisplayName("입고 승인 api")
    fun registInbound() {
        val inbound = inboundRepository.save(InboundFixture().inbound())

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/api/inbounds/${inbound}/confirm")
            .then()
            .log().all()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    @DisplayName("입고 승인 실패 api")
    fun registInboundFail() {
        val inbound = inboundRepository.save(InboundFixture().completedInbound())

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .`when`()
            .patch("/api/inbounds/${inbound}/confirm")
            .then()
            .log().all()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
    }
}