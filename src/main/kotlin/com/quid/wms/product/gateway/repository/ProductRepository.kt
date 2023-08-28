package com.quid.wms.product.gateway.repository

import com.quid.wms.product.domain.Product

interface ProductRepository {
    fun findAll(): List<Product>
    fun save(product: Product)
}

class ProductMemoryRepository: ProductRepository {
    private val products = mutableMapOf<Long, Product>()

    override fun findAll(): List<Product> {
        return products.values.toList()
    }

    override fun save(product: Product) {
        products[product.id] = product
    }
}