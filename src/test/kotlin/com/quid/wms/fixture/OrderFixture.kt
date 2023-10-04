package com.quid.wms.fixture

import com.quid.wms.outbound.domain.DeliveryInfo
import com.quid.wms.outbound.domain.Order
import com.quid.wms.outbound.domain.OrderCustomer
import com.quid.wms.outbound.domain.OrderProduct
import com.quid.wms.outbound.gateway.repository.OrderRepository

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
    override fun save(order: Order): Order = list.add(order).let { order }
    override fun findAll(): List<Order> = list
    override fun findById(orderId: Long): Order = list.first { it.id == orderId }
    override fun findByName(name: String): List<Order> {
        return list.filter { it.orderCustomer.name == name }
    }
}