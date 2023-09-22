package com.quid.wms.order.domain

data class Order(
    val id: Long? = null,
    val orderCustomer: OrderCustomer,
    val deliveryInfo: DeliveryInfo,
    val orderProducts: List<OrderProduct> = listOf(),
) {
    val totalPrice: Int
        get() {
            return orderProducts.sumOf { it.totalPrice }
        }
}

fun createOrder(
    orderCustomer: OrderCustomer,
    deliveryInfo: DeliveryInfo,
    orderProducts: List<OrderProduct>,
): Order {
    return Order(
        orderCustomer = orderCustomer,
        deliveryInfo = deliveryInfo,
        orderProducts = orderProducts,
    )
}