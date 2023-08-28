package com.quid.wms.product

import com.quid.wms.product.domain.*
import com.quid.wms.product.gateway.repository.ProductMemoryRepository
import com.quid.wms.product.gateway.repository.ProductRepository
import com.quid.wms.product.gateway.web.dto.RegisterProductRequest
import com.quid.wms.product.usecase.RegisterProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RegisterProductTest {

    private lateinit var registerProduct: RegisterProduct
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        productRepository = ProductMemoryRepository()
        registerProduct = RegisterProduct(productRepository)
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

        assertThat(productRepository.findAll()).hasSize(1)
    }
}

