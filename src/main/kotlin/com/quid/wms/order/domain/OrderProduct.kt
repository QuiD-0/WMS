package com.quid.wms.order.domain

import com.quid.wms.product.domain.Product

data class OrderProduct(
    val id: Long? = null,
    val productId: Long,
    val quantity: Int,
    val unitPrice: Int,
) {
    val totalPrice: Int
        get() {
            return quantity * unitPrice
        }

    init {
        require(quantity > 0) { "quantity must be greater than 0" }
    }

}

fun orderProduct(product: Product, quantity: Int): OrderProduct {
    return OrderProduct(
        productId = product.id!!,
        quantity = quantity,
        unitPrice = product.price,
    )
}