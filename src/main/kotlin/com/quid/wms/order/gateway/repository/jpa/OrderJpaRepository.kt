package com.quid.wms.order.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderEntity, Long> {
}