package com.quid.wms.inbound

import com.quid.wms.annotation.ApiTest
import com.quid.wms.fixture.InboundFixture
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

@ApiTest
@Disabled
class RegisterInboundApiTest {

    @Test
    @DisplayName("입고 등록 api")
    fun registInbound() {
        val request = InboundFixture().registRequest()

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/api/inbounds")
            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
    }
}