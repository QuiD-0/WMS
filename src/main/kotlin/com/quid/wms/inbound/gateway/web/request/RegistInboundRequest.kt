package com.quid.wms.inbound.gateway.web.request

import java.time.LocalDateTime

data class RegisterInboundRequest(
    val title: String,
    val description: String,
    val orderRequestAt: LocalDateTime,
    val estimateArrivalAt: LocalDateTime, val item: List<Long>
)