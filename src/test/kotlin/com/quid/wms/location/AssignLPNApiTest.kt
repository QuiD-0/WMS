package com.quid.wms.location

import com.quid.wms.annotation.ApiTest
import com.quid.wms.fixture.LocationFixture
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

@ApiTest
@Disabled
class AssignLPNApiTest {

    @Test
    @DisplayName("location 등록 api")
    fun registerLocation() {
        val request = LocationFixture().assignLPNRequest()

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/api/locations/register-lpn")
            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
    }

}