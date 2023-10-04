package com.quid.wms.outbound.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderEntity, Long> {
    fun findByName(name: String): List<OrderEntity>
}