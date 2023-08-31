package com.quid.wms.inbound.gateway.web.request

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.domain.InboundItem
import java.time.LocalDateTime

data class RegisterInboundRequest(
    val title: String,
    val description: String,
    val orderRequestAt: LocalDateTime,
    val estimateArrivalAt: LocalDateTime,
    val item: List<Long>
) {
    fun toDomain(inboundItem: List<InboundItem>) =
        Inbound(null, title, description, orderRequestAt, estimateArrivalAt, inboundItem)
}