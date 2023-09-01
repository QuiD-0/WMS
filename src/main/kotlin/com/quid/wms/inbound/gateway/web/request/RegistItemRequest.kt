package com.quid.wms.inbound.gateway.web.request

import com.quid.wms.inbound.domain.InboundItem
import com.quid.wms.product.domain.Product

data class RegistItemRequest(
    val productId: Long,
    val quantity: Long,
    val unitPrice: Long
) {
    fun toInboundItem(product: Product) = InboundItem(null, product, quantity, unitPrice)
}