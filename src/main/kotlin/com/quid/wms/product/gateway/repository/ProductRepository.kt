package com.quid.wms.product.gateway.repository

import com.quid.wms.product.domain.Product
import com.quid.wms.product.gateway.repository.jpa.ProductJpaRepository
import com.quid.wms.product.gateway.repository.jpa.productEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface ProductRepository {
    fun findAll(): List<Product>
    fun save(product: Product): Product
    fun deleteAll()
    fun isExistByCode(code: String): Boolean
    fun findById(productId: Long): Product

    @Repository
    class ProductRepositoryImpl(
        private val jpaRepository: ProductJpaRepository
    ) : ProductRepository {

        override fun findAll(): List<Product> = jpaRepository.findAll().map { it.toProduct() }


        override fun save(product: Product): Product =
            jpaRepository.save(productEntity(product)).toProduct()

        override fun deleteAll() = jpaRepository.deleteAll()

        override fun isExistByCode(code: String): Boolean = jpaRepository.existsByCode(code)

        override fun findById(productId: Long): Product = jpaRepository.findByIdOrNull(productId)
            ?.toProduct()
            ?: throw IllegalArgumentException("존재하지 않는 상품입니다.")
    }
}

