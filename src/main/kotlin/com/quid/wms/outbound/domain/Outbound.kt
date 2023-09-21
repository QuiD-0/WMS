package com.quid.wms.outbound.domain

data class Outbound(
    val id: Long? = null,
    val orderId: Long,
    val deliveryRequirements: String,
    val isPriorityDelivery: Boolean,
    val desiredDeliveryAt: String,
    val pickingLocationId: Long,
    val outboundStatus: OutboundStatus,
) {
}