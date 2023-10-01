package com.quid.wms.order.usecase

import com.quid.wms.order.domain.Order
import com.quid.wms.order.gateway.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FindOrder {
    fun all(): List<Order>
    fun byName(name: String): List<Order>

    @Service
    @Transactional(readOnly = true)
    class FindOrderImpl(
        private val orderRepository: OrderRepository,
    ) : FindOrder {
        override fun all(): List<Order> = orderRepository.findAll()
        override fun byName(name: String): List<Order> = orderRepository.findByName(name)
    }
}