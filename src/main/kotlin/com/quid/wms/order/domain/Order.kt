package com.quid.wms.order.domain

data class Order(
    val id: Long? = null,
    val orderCustomer : OrderCustomer,
    val deliveryInfo : DeliveryInfo,
    val orderProducts: List<OrderProduct> = listOf(),
) {
    fun getTotalPrice(): Int {
        return orderProducts.sumOf { it.totalPrice() }
    }
}