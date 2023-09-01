package com.quid.wms.inbound

import com.quid.wms.annotation.ApiTest
import com.quid.wms.fixture.InboundFixture
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

@ApiTest
class RegisterInboundApiTest {

    @Test
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