package com.quid.wms.order.domain

data class OrderProduct(
    val id: Long? = null,
    val productId: Long,
    val quantity: Int,
) {
    init {
        require(quantity > 0) { "quantity must be greater than 0" }
    }
}