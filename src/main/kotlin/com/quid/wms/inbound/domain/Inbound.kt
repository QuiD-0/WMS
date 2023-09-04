package com.quid.wms.inbound.domain

import java.time.LocalDateTime

data class Inbound(
    val id: Long? = null,
    val title: String,
    val description: String,
    val orderRequestAt: LocalDateTime,
    val estimateArrivalAt: LocalDateTime,
    val inboundItems: List<InboundItem>,
    val status: InboundStatus,
) {
    init {
        require(title.isNotBlank()) { "title is invalid" }
        require(description.isNotBlank()) { "description is invalid" }
        require(orderRequestAt.isAfter(LocalDateTime.now().minusDays(1))) { "orderRequestAt is invalid" }
        require(estimateArrivalAt.isAfter(orderRequestAt)) { "estimateArrivalAt is invalid" }
        require(inboundItems.isNotEmpty()) { "items is invalid" }
    }

    fun confirm() = if (status == InboundStatus.REQUESTED) copy(status = InboundStatus.CONFIRMED)
        else throw IllegalStateException("입고 요청상태가 아닙니다.")

    fun reject() = if (status == InboundStatus.REQUESTED) copy(status = InboundStatus.REJECTED)
        else throw IllegalStateException("입고 요청상태가 아닙니다.")
}