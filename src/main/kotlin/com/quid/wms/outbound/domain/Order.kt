package com.quid.wms.outbound.domain

data class Order(
    val id: Long? = null,
    val orderCustomer : OrderCustomer,
    val orderProducts: List<OrderProduct>,
) {
    fun checkQuantity() {
        orderProducts.forEach {
            if( it.quantity <= 0 ) throw IllegalArgumentException("quantity must be greater than 0")
        }
    }

    fun getTotalPrice(): Long {
        return orderProducts.sumOf { it.unitPrice * it.quantity }
    }
}