package com.quid.wms.outbound.domain

import com.quid.wms.location.domain.Location

class Outbound(
    val id: Long? = null,
    val order: Order,
    val deliveryRequirements: String,
    val isPriorityDelivery: Boolean,
    val desiredDeliveryAt: String,
    val pickingTote: Location
) {
}