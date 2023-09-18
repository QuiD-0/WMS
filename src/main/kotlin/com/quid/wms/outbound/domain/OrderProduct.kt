package com.quid.wms.outbound.domain

import com.quid.wms.product.domain.Product

data class OrderProduct(
    val id: Long? = null,
    val product: Product,
    val quantity: Int,
    val unitPrice: Long,
) {
}