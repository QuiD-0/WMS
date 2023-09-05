package com.quid.wms.inbound.domain

import com.quid.wms.inbound.domain.InboundStatus.*
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
        require(inboundItems.isNotEmpty()) { "items is invalid" }
    }

    fun confirm() = isRequested().copy(status = CONFIRMED)

    fun reject(message: String) = isRequested().copy(status = REJECTED, rejectReason = message)

    private fun isRequested(): Inbound {
        if (this.status != REQUESTED) throw IllegalStateException("입고 요청 상태가 아닙니다.")
        return this
    }
}
