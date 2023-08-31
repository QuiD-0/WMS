package com.quid.wms.fixture

import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.Product
import com.quid.wms.product.domain.ProductSize
import com.quid.wms.product.domain.TemperatureZone
import com.quid.wms.product.gateway.repository.ProductRepository

class ProductFixture {

    fun product() = Product(
        1L,
        "name",
        "code",
        "description",
        "brand",
        "maker",
        "origin",
        Category.ELECTRONICS,
        TemperatureZone.ROOM_TEMPERATURE,
        1000L,
        ProductSize(100L, 100L, 100L)
    )

    fun productRepository() = ProductRepositoryFixture()
}

class ProductRepositoryFixture: ProductRepository {
    private val products = mutableMapOf<Long, Product>()

    override fun findAll(): List<Product> {
        return products.values.toList()
    }

    override fun save(product: Product) {
        products[product.id!!] = product
    }

    override fun deleteAll() {
        products.clear()
    }

    override fun isExistByCode(code: String): Boolean {
        return products.values.any { it.code == code }
    }
}