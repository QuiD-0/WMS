package com.quid.wms.inbound.domain

data class InboundItem(
    val id: Long? = null,
    val productId: Long,
    val quantity: Long,
    val unitPrice: Long,
    val inboundId: Long,
){
    init {
        require(quantity > 0) { "quantity is invalid" }
        require(unitPrice > 0) { "unitPrice is invalid" }
    }
}