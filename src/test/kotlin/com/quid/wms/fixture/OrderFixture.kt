package com.quid.wms.fixture

import com.quid.wms.order.domain.DeliveryInfo
import com.quid.wms.order.domain.Order
import com.quid.wms.order.domain.OrderCustomer
import com.quid.wms.order.domain.OrderProduct
import com.quid.wms.order.gateway.repository.OrderRepository

class OrderFixture {

    val order = Order(
        id = 1L, orderCustomer = OrderCustomer(
            name = "name",
            email = "email",
            phone = "phone",
        ), deliveryInfo = DeliveryInfo(
            address = "address",
            zipCode = "zipCode",
            memo = "memo",
        ), orderProducts = listOf(
            OrderProduct(
                id = 1L,
                productId = 1L,
                quantity = 1,
                unitPrice = 1,
            )
        )
    )

    fun repository(): OrderRepository = OrderRepositoryFixture()
}

private class OrderRepositoryFixture : OrderRepository {
    val list= mutableListOf<Order>()
    override fun save(order: Order): Long = list.add(order).let { 1L }
    override fun findAll(): List<Order> = list
    override fun findById(orderId: Long): Order = list.first { it.id == orderId }
}