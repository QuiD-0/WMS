package com.quid.wms.product.usecase

import com.quid.wms.product.gateway.repository.ProductRepository
import com.quid.wms.product.RegisterProductRequest

class RegisterProduct(
    private val productRepository: ProductRepository
) {
    fun request(request: RegisterProductRequest) {
        val product = request.toDomain()
        productRepository.save(product)
    }
}