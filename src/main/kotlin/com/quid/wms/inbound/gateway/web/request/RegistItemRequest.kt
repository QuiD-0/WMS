package com.quid.wms.inbound.gateway.web.request

import com.quid.wms.inbound.domain.InboundItem

data class RegistItemRequest(
    val productId: Long,
    val quantity: Long,
    val unitPrice: Long
) {
    fun toInboundItem() = InboundItem(null, productId, quantity, unitPrice)
}