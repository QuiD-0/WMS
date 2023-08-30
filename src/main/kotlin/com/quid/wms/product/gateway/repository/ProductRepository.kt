package com.quid.wms.product.gateway.repository

import com.quid.wms.product.domain.Product
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

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
            jpaRepository.save(fromDomain(product))
        }

        override fun deleteAll() {
            jpaRepository.deleteAll()
        }

        override fun isExistByCode(code: String): Boolean {
            return jpaRepository.existsByCode(code)
        }
    }
}

