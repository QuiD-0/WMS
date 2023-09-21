package com.quid.wms.order.domain

data class Order(
    val id: Long? = null,
    val orderCustomer : OrderCustomer,
    val orderProducts: List<OrderProduct> = listOf(),
) {
}