package com.quid.wms.product

import com.quid.wms.annotation.ApiTest
import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.TemperatureZone
import com.quid.wms.product.gateway.repository.ProductRepository
import com.quid.wms.product.gateway.web.dto.RegistProductRequest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional

@ApiTest
class RegisterProductTest {

    @Autowired
    lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        productRepository.deleteAll()
    }

    @Test
    @DisplayName("상품 등록 api")
    fun registerProduct() {
        val request = RegistProductRequest(
            "name",
            "code",
            "description",
            "brand",
            "maker",
            "origin",
            Category.ELECTRONICS,
            TemperatureZone.ROOM_TEMPERATURE,
            1000L,
            100L,
            100L,
            100L
        )

        RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/products")
            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
    }
}

