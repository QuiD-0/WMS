package com.quid.wms.outbound.domain

data class OrderProduct(
    val id: Long? = null,
    val productId: Long,
    val quantity: Int,
) {
}