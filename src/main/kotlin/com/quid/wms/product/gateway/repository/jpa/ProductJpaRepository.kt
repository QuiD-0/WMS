package com.quid.wms.product.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<ProductEntity, Long> {
    fun findByCode(code: String): ProductEntity?
}