package com.quid.wms.outbound.gateway.repository

import com.quid.wms.outbound.domain.Order
import org.springframework.stereotype.Repository

interface OrderRepository {
    fun findById(orderId: Long): Order

    @Repository
    class OrderRepositoryImpl : OrderRepository {
        override fun findById(orderId: Long): Order {
            return Any() as Order
        }

    }
}