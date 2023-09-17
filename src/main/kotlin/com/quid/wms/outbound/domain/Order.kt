package com.quid.wms.outbound.domain

data class Order(
    val orderId: Long,
    val orderCustomer : OrderCustomer,
    val orderProducts: List<OrderProduct>,
) {
    fun checkQuantity() {
        orderProducts.forEach {
            if( it.quantity <= 0 ) throw IllegalArgumentException("quantity must be greater than 0")
        }
    }
}