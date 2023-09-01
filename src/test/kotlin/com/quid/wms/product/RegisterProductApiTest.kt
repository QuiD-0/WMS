package com.quid.wms.product

import com.quid.wms.annotation.ApiTest
import com.quid.wms.fixture.ProductFixture
import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.TemperatureZone
import com.quid.wms.product.gateway.repository.ProductRepository
import com.quid.wms.product.gateway.web.request.RegistProductRequest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@ApiTest
class RegisterProductApiTest {

    @Test
    @DisplayName("상품 등록 api")
    fun registerProduct() {
        val dateCode: String = LocalDateTime.now().toString()
        val request = ProductFixture().registProductRequest(dateCode)

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/api/products")
            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
    }
}

