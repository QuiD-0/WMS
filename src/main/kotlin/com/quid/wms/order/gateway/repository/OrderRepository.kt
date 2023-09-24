package com.quid.wms.order.gateway.repository

import com.quid.wms.order.domain.Order
import com.quid.wms.order.gateway.repository.jpa.OrderJpaRepository
import com.quid.wms.order.gateway.repository.jpa.orderEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface OrderRepository {
    fun save(order: Order): Long
    fun findAll(): List<Order>
    fun findById(orderId: Long): Order

    @Repository
    class OrderRepositoryImpl(
        private val orderJpaRepository: OrderJpaRepository,
    ) : OrderRepository {
        override fun save(order: Order): Long = orderJpaRepository.save(orderEntity(order)).id!!
        override fun findAll(): List<Order> = orderJpaRepository.findAll().map { it.toOrder() }
        override fun findById(orderId: Long): Order = orderJpaRepository
            .findByIdOrNull(orderId)
            ?.toOrder()
            ?: throw IllegalArgumentException("Order not found")

    }
}