package com.quid.wms.order.usecase

import com.quid.wms.order.domain.Order
import com.quid.wms.order.gateway.repository.OrderRepository
import org.springframework.stereotype.Service

interface FindOrder {
    fun all(): List<Order>

    @Service
    class FindOrderImpl(
        private val orderRepository: OrderRepository,
    ) : FindOrder {
        override fun all(): List<Order> = orderRepository.findAll()
    }
}