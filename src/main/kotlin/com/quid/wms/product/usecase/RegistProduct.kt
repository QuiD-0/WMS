package com.quid.wms.product.usecase

import com.quid.wms.product.domain.Product
import com.quid.wms.product.gateway.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RegistProduct {
    fun register(product: Product)

    @Service
    @Transactional
    class RegistProductUseCase(
        private val productRepository: ProductRepository
    ) : RegistProduct {
        override fun register(product: Product) {
            productRepository.save(product)
        }
    }
}
