package com.quid.wms.fixture

import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.Product
import com.quid.wms.product.domain.ProductSize
import com.quid.wms.product.domain.TemperatureZone
import com.quid.wms.product.gateway.repository.ProductRepository
import com.quid.wms.product.gateway.web.request.RegistProductRequest
import java.time.LocalDateTime

class ProductFixture {

    fun product(code: String = LocalDateTime.now().toString()) = Product(
        1L,
        "name",
        code,
        10000,
        "description",
        "brand",
        "maker",
        "origin",
        Category.ELECTRONICS,
        TemperatureZone.ROOM_TEMPERATURE,
        1000L,
        ProductSize(100L, 100L, 100L)
    )

    fun registProductRequest(code: String = LocalDateTime.now().toString()) = RegistProductRequest(
        "name",
        code,
        10000,
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

    fun productRepository() = ProductRepositoryFixture()
}

class ProductRepositoryFixture: ProductRepository {
    private val products = mutableMapOf<Long, Product>()

    override fun findAll(): List<Product> {
        return products.values.toList()
    }

    override fun save(product: Product): Long {
        if(products.containsKey(product.id)) throw IllegalArgumentException("Product already exists")
        products[1] = product
        return 1
    }

    override fun deleteAll() {
        products.clear()
    }

    override fun findById(productId: Long): Product {
        return products[productId]!!
    }
}