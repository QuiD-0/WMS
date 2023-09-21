package com.quid.wms.outbound.domain

import java.time.LocalDate

data class Outbound(
    val id: Long? = null,
    val orderId: Long,
    val isPriorityDelivery: Boolean,
    val desiredDeliveryDateTime: LocalDate,
    val pickingLocationId: Long,
    val outboundStatus: OutboundStatus,
) {
}