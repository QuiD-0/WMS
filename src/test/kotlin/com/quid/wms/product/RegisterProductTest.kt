package com.quid.wms.product

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RegisterProductTest {

    private lateinit var registerProduct: RegisterProduct

    @BeforeEach
    fun setUp() {
        registerProduct = RegisterProduct()
    }

    @Test
    @DisplayName("상품을 등록한다.")
    fun registerProduct() {
        val request = RegisterProductRequest(
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
        registerProduct.request(request)

//        assertThat(productRepository.findAll()).hasSize(1)
    }
}

class RegisterProduct {
    fun request(request: RegisterProductRequest) {
        println(request)
    }
}

data class RegisterProductRequest(
    val name: String,
    val code: String,
    val description: String,
    val brand: String,
    val maker: String,
    val origin: String,
    val category: Category,
    val temperatureZone: TemperatureZone,
    val weight: Long,
    val widthInMillimeters: Long,
    val heightInMillimeters: Long,
    val lengthInMillimeters: Long,

    )

enum class Category(
    val description: String
) {
    ELECTRONICS("전자 제품")
}

enum class TemperatureZone(
    val description: String
) {
    ROOM_TEMPERATURE("상온"),
}

