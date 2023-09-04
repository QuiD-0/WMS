package com.quid.wms.inbound.gateway.web.request

import java.time.LocalDateTime

class RegisterLPNRequest(
    val inboundItemId: Long,
    val lpnBarcode: String,
    val expirationAt: LocalDateTime,
)
