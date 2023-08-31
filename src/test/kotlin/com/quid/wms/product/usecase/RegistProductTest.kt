package com.quid.wms.product.usecase

import com.quid.wms.fixture.ProductFixture
import com.quid.wms.product.usecase.RegistProduct.RegistProductUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class RegistProductTest{

    private val registProduct: RegistProduct = RegistProductUseCase(ProductFixture().productRepository())

    @Test
    @DisplayName("상품을 등록한다.")
    fun registProduct() {
        val product = ProductFixture().product()

        assertDoesNotThrow { registProduct.register(product) }
    }

    @Test
    @DisplayName("이미 존재하는 상품 코드일 경우 예외를 발생시킨다.")
    fun registProductWithExistCode() {
        val product = ProductFixture().product()

        registProduct.register(product)

        assertThrows<IllegalArgumentException> { registProduct.register(product) }
    }

}