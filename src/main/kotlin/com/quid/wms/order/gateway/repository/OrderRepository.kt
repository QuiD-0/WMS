package com.quid.wms.order.gateway.repository

import com.quid.wms.order.domain.Order
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