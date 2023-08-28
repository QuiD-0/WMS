package com.quid.wms.product

import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.TemperatureZone
import com.quid.wms.product.gateway.repository.ProductMemoryRepository
import com.quid.wms.product.gateway.repository.ProductRepository
import com.quid.wms.product.gateway.web.dto.RegistProductRequest
import com.quid.wms.product.usecase.RegistProduct
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterProductTest {

    @LocalServerPort
    var serverPort: Int = 0

    private lateinit var registProduct: RegistProduct
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        if (RestAssured.UNDEFINED_PORT == RestAssured.port) {
            RestAssured.port = serverPort
        }
        productRepository = ProductMemoryRepository()
        registProduct = RegistProduct.RegistProductUseCase(productRepository)
    }

    @Test
    @DisplayName("상품을 등록한다.")
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
//        registerProduct.request(request)
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

