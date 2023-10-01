package com.quid.wms.order.domain

import com.quid.wms.product.domain.Product

data class OrderProduct(
    val id: Long? = null,
    val productId: Long,
    val quantity: Long,
    val unitPrice: Int,
) {
    val totalPrice: Long
        get() {
            return quantity * unitPrice
        }

    init {
        require(quantity > 0) { "quantity must be greater than 0" }
    }

}

fun orderProduct(product: Product, quantity: Long): OrderProduct {
    return OrderProduct(
        productId = product.id!!,
        quantity = quantity,
        unitPrice = product.price,
    )
}