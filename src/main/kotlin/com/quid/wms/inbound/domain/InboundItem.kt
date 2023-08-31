package com.quid.wms.inbound.domain

data class InboundItem(
    val id: Long? = null,
    val productId: Long,
    val quantity: Long,
    val unitPrice: Long
){
    init {
        require(productId > 0) { "productId is invalid" }
        require(quantity > 0) { "quantity is invalid" }
        require(unitPrice > 0) { "unitPrice is invalid" }
    }
}