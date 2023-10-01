package com.quid.wms.order.gateway.repository

import com.quid.wms.order.domain.Order
import com.quid.wms.order.gateway.repository.jpa.OrderJpaRepository
import com.quid.wms.order.gateway.repository.jpa.orderEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface OrderRepository {
    fun save(order: Order): Order
    fun findAll(): List<Order>
    fun findById(orderId: Long): Order
    fun findByName(name: String): List<Order>

    @Repository
    class OrderRepositoryImpl(
        private val orderJpaRepository: OrderJpaRepository,
    ) : OrderRepository {
        override fun save(order: Order): Order = orderJpaRepository.save(orderEntity(order)).toOrder()
        override fun findAll(): List<Order> = orderJpaRepository.findAll().map { it.toOrder() }
        override fun findById(orderId: Long): Order = orderJpaRepository
            .findByIdOrNull(orderId)
            ?.toOrder()
            ?: throw IllegalArgumentException("Order not found")

        override fun findByName(name: String): List<Order> = orderJpaRepository.findByName(name).map { it.toOrder() }
    }
}