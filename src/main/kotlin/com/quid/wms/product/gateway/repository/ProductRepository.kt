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
    fun findById(productId: Long): Product

    @Repository
    class ProductRepositoryImpl(
        private val jpaRepository: ProductJpaRepository
    ) : ProductRepository {

        override fun findAll(): List<Product> = jpaRepository.findAll().map { it.toProduct() }


        override fun save(product: Product): Product =
            jpaRepository.findByCode(product.code)
                ?.let { throw IllegalArgumentException("이미 존재하는 상품입니다.") }
                ?: jpaRepository.save(productEntity(product)).toProduct()

        override fun deleteAll() = jpaRepository.deleteAll()

        override fun findById(productId: Long): Product = jpaRepository.findByIdOrNull(productId)
            ?.toProduct()
            ?: throw IllegalArgumentException("존재하지 않는 상품입니다.")
    }
}

