package com.quid.wms.location.domain

import java.time.LocalDateTime

data class LPN(
    val id: Long? = null,
    val productId: Long,
    val inboundItemId: Long,
    val lpnBarcode: String,
    val expirationAt: LocalDateTime,
    val quantity: Long,
) {
    init {
        require(lpnBarcode.isNotBlank()) { "lpnBarcode is invalid" }
    }

    fun isEqual(other: LPN): Boolean {
        return lpnBarcode == other.lpnBarcode
    }

    fun increaseQuantity(): LPN {
        return copy(quantity = quantity + 1)
    }
}