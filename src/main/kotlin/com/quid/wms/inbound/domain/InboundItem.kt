package com.quid.wms.inbound.domain

import com.quid.wms.product.domain.Product

data class InboundItem(
    val id: Long? = null,
    val product: Product,
    val quantity: Long,
){

    init {
        require(quantity > 0) { "quantity is invalid" }
    }
}