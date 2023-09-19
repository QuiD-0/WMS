package com.quid.wms.location.gateway.web.request

import com.quid.wms.location.domain.LPN
import java.time.LocalDateTime

data class RegisterLPNRequest(
    val inboundItemId: Long,
    val lpnBarcode: String,
    val expirationAt: LocalDateTime,
){
    fun toLPN(productId: Long) = LPN(null, productId, inboundItemId, lpnBarcode, expirationAt)
}
