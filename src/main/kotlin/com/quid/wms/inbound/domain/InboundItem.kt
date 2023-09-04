package com.quid.wms.inbound.domain

import com.quid.wms.product.domain.Product

data class InboundItem(
    val id: Long? = null,
    val product: Product,
    val quantity: Long,
    val unitPrice: Long,
    val lpnList: List<LPN> = emptyList()
){
    init {
        require(quantity > 0) { "quantity is invalid" }
        require(unitPrice > 0) { "unitPrice is invalid" }
    }
}