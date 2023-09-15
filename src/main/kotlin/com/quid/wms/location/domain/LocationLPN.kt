package com.quid.wms.location.domain

import com.quid.wms.inbound.domain.LPN

data class LocationLPN(
    val id: Long? = null,
    val lpn: LPN,
    val quantity: Long = 1,
) {
    init {
        require(quantity >= 0) { "quantity is invalid" }
    }

    fun increaseQuantity(): LocationLPN {
        return copy(quantity = quantity + 1)
    }
}