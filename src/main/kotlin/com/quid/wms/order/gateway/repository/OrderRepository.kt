package com.quid.wms.order.gateway.repository

import com.quid.wms.order.domain.Order
import com.quid.wms.order.gateway.repository.jpa.OrderJpaRepository
import com.quid.wms.order.gateway.repository.jpa.orderEntity
import org.springframework.stereotype.Repository

interface OrderRepository {
    fun existsById(orderId: Long): Boolean
    fun save(order: Order): Long
    fun findAll(): List<Order>

    @Repository
    class OrderRepositoryImpl(
        private val orderJpaRepository: OrderJpaRepository,
    ) : OrderRepository {
        override fun existsById(orderId: Long): Boolean = orderJpaRepository.existsById(orderId)
        override fun save(order: Order): Long = orderJpaRepository.save(orderEntity(order)).id!!
        override fun findAll(): List<Order> = orderJpaRepository.findAll().map { it.toOrder() }
    }
}