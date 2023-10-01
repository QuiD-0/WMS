package com.quid.wms.order.domain

data class Order(
    val id: Long? = null,
    val orderCustomer: OrderCustomer,
    val deliveryInfo: DeliveryInfo,
    val orderProducts: List<OrderProduct> = listOf(),
) {
    val totalPrice: Long
        get() {
            return orderProducts.sumOf { it.totalPrice }
        }

    fun findProductIdList(): List<Long> {
        return orderProducts.map { it.productId }
    }
}

fun createOrder(
    orderCustomer: OrderCustomer,
    deliveryInfo: DeliveryInfo,
    orderProducts: List<OrderProduct>,
): Order = Order(
    orderCustomer = orderCustomer,
    deliveryInfo = deliveryInfo,
    orderProducts = orderProducts,
)