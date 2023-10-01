package com.quid.wms.product.gateway.repository

import com.quid.wms.order.domain.OrderProduct
import com.quid.wms.order.domain.orderProduct
import com.quid.wms.outbound.domain.ProductQuantity
import com.quid.wms.product.domain.Product
import com.quid.wms.product.gateway.repository.jpa.ProductJpaRepository
import com.quid.wms.product.gateway.repository.jpa.productEntity
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface ProductRepository {
    fun findAll(): List<Product>
    fun save(product: Product): Product
    fun deleteAll()
    fun findById(productId: Long): Product
    fun findOrderProducts(productList: List<ProductQuantity>): List<OrderProduct>

    @Repository
    class ProductRepositoryImpl(
        private val jpaRepository: ProductJpaRepository
    ) : ProductRepository {

        override fun findAll(): List<Product> = jpaRepository.findAll().map { it.toProduct() }

        override fun save(product: Product): Product = jpaRepository.save(productEntity(product)).toProduct()

        override fun deleteAll() = jpaRepository.deleteAll()

        override fun findById(productId: Long): Product = jpaRepository.findByIdOrNull(productId)
            ?.toProduct()
            ?: throw EntityNotFoundException("존재하지 않는 상품입니다.")

        override fun findOrderProducts(productList: List<ProductQuantity>): List<OrderProduct> {
            return productList.map {
                val product = findById(it.productId)
                orderProduct(product, it.quantity)
            }
        }

    }
}

