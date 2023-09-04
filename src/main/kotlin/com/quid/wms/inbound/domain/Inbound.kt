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
    val rejectReason :String = ""
) {

    init {
        require(title.isNotBlank()) { "title is invalid" }
        require(description.isNotBlank()) { "description is invalid" }
        require(orderRequestAt.isBefore(estimateArrivalAt)) { "orderRequestAt is invalid" }
        require(estimateArrivalAt.isAfter(orderRequestAt)) { "estimateArrivalAt is invalid" }
        require(inboundItems.isNotEmpty()) { "items is invalid" }
    }

    fun confirm() = isRequested().copy(status = InboundStatus.CONFIRMED)

    fun reject(message: String) = isRequested().copy(status = InboundStatus.REJECTED, rejectReason = message)

    private fun isRequested(): Inbound {
        if (this.status != InboundStatus.REQUESTED) throw IllegalStateException("입고 요청 상태가 아닙니다.")
        return this
    }
}
