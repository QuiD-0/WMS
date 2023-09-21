package com.quid.wms.product.usecase

import com.quid.wms.product.domain.Product
import com.quid.wms.product.gateway.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FindProduct {
    fun all(): List<Product>

    @Service
    @Transactional(readOnly = true)
    class FindProductUseCase(
        private val productRepository: ProductRepository
    ) : FindProduct {
        override fun all(): List<Product> = productRepository.findAll()
    }
}