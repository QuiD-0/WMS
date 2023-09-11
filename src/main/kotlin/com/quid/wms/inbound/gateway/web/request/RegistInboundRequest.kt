package com.quid.wms.inbound.gateway.web.request

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.domain.InboundStatus.REQUESTED
import java.time.LocalDateTime

data class RegistInboundRequest(
    val title: String,
    val description: String,
    val orderRequestAt: LocalDateTime,
    val estimateArrivalAt: LocalDateTime,
    val inboundItems: List<RegistItemRequest>,
) {
    fun toInbound() = Inbound(null, title, description, orderRequestAt, estimateArrivalAt, REQUESTED)
}