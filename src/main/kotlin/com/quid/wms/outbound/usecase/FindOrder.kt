package com.quid.wms.outbound.usecase

import com.quid.wms.outbound.domain.Order
import com.quid.wms.outbound.gateway.repository.OrderRepository
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