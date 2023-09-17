package com.quid.wms.outbound.domain

import com.quid.wms.product.domain.Product

class OrderProduct(
    val product: Product,
    val quantity: Int,
    val unitProduct: Long,
) {
}