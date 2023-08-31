package com.quid.wms.inbound.domain

import java.time.LocalDateTime

data class Inbound(
    val id: Long? = null,
    val title: String,
    val description: String,
    val orderRequestAt: LocalDateTime,
    val estimateArrivalAt: LocalDateTime,
    val inboundItems: List<InboundItem>
) {
    init {
        require(title.isNotBlank()) { "title is invalid" }
        require(description.isNotBlank()) { "description is invalid" }
        require(orderRequestAt.isAfter(LocalDateTime.now().minusDays(1))) { "orderRequestAt is invalid" }
        require(estimateArrivalAt.isAfter(orderRequestAt)) { "estimateArrivalAt is invalid" }
        require(inboundItems.isNotEmpty()) { "items is invalid" }
    }
}