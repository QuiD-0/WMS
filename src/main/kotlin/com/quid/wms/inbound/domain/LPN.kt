package com.quid.wms.inbound.domain

import java.time.LocalDateTime

data class LPN(
    val id: Long? = null,
    val lpnBarcode: String,
    val expirationAt: LocalDateTime,
    val inboundItemId: Long
) {
    init {
        require(lpnBarcode.isNotBlank()) { "lpnBarcode is invalid" }
    }
}