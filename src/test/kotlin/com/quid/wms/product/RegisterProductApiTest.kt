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
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@ApiTest
@Disabled
class RegisterProductApiTest {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    @DisplayName("상품 등록 api")
    fun registerProduct() {
        val request = ProductFixture().registProductRequest()

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/api/products")
            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
    }

    @Test
    @DisplayName("상품 등록 실패 api")
    fun registerProductFail() {
        productRepository.save(ProductFixture().product("code"))
        val request = ProductFixture().registProductRequest("code")

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/api/products")
            .then()
            .log().all()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
    }
}

