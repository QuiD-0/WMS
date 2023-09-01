package com.quid.wms.product.gateway.repository

import com.quid.wms.product.domain.Product
import com.quid.wms.product.gateway.repository.jpa.ProductJpaRepository
import com.quid.wms.product.gateway.repository.jpa.productEntity
import org.springframework.stereotype.Repository

interface ProductRepository {
    fun findAll(): List<Product>
    fun save(product: Product)
    fun deleteAll()
    fun isExistByCode(code: String): Boolean

    @Repository
    class ProductRepositoryImpl(
        private val jpaRepository: ProductJpaRepository
    ): ProductRepository {

        override fun findAll(): List<Product> {
            return jpaRepository.findAll().map { it.toDomain() }
        }

        override fun save(product: Product) {
            jpaRepository.save(productEntity(product))
        }

        override fun deleteAll() {
            jpaRepository.deleteAll()
        }

        override fun isExistByCode(code: String): Boolean {
            return jpaRepository.existsByCode(code)
        }
    }
}

