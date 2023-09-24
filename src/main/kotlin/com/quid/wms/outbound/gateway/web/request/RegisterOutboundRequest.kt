package com.quid.wms.outbound.gateway.web.request

import com.quid.wms.outbound.domain.Outbound
import com.quid.wms.outbound.domain.OutboundStatus
import java.time.LocalDate

data class RegisterOutboundRequest(
    val orderId: Long,
    val isPriorityDelivery: Boolean,
    val desiredDeliveryDate: LocalDate,
) {
    init {
        require(orderId > 0) { "orderId must be greater than 0" }
    }

    fun toOutbound(lpnIdList: List<Long>): Outbound {
        return Outbound(
            orderId = orderId,
            isPriorityDelivery = isPriorityDelivery,
            desiredDeliveryDateTime = desiredDeliveryDate,
            pickingLocationIdList = lpnIdList,
            outboundStatus = OutboundStatus.CREATED
        )
    }
}