package com.quid.wms.order.gateway.repository

import org.springframework.stereotype.Repository

interface OrderRepository {
    fun existsById(orderId: Long): Boolean

    @Repository
    class OrderRepositoryImpl : OrderRepository {
        override fun existsById(orderId: Long): Boolean {
            return true
        }
    }
}