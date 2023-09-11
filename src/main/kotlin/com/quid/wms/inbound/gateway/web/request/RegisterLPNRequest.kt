package com.quid.wms.inbound.gateway.web.request

import com.quid.wms.inbound.domain.LPN
import java.time.LocalDateTime

data class RegisterLPNRequest(
    val inboundItemId: Long,
    val lpnBarcode: String,
    val expirationAt: LocalDateTime,
){
    fun toLPN() = LPN(null, lpnBarcode, expirationAt, inboundItemId)
}
