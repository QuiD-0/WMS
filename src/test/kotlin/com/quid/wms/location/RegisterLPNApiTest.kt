package com.quid.wms.location

import com.quid.wms.annotation.ApiTest
import com.quid.wms.fixture.LPNFixture
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

@ApiTest
@Disabled
class RegisterLPNApiTest {

    @Test
    fun registerLPN() {
        val request = LPNFixture().registerRequest()

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/api/locations/lpns")
            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
    }
}