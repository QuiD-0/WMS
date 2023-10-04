package com.quid.wms.outbound.domain

import java.time.LocalDate

data class Outbound(
    val id: Long? = null,
    val order: Order,
    val isPriorityDelivery: Boolean,
    val desiredDeliveryDateTime: LocalDate,
    val pickingLocationIdList: List<Long>,
    val outboundStatus: OutboundStatus,
) {
}