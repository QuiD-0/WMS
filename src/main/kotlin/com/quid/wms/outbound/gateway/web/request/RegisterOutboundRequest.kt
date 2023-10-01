package com.quid.wms.outbound.gateway.web.request

import com.quid.wms.order.domain.DeliveryInfo
import com.quid.wms.order.domain.Order
import com.quid.wms.outbound.domain.Outbound
import com.quid.wms.outbound.domain.OutboundStatus
import com.quid.wms.outbound.domain.ProductQuantity
import java.time.LocalDate

data class RegisterOutboundRequest(
    val isPriorityDelivery: Boolean,
    val desiredDeliveryDate: LocalDate,
    val userId: Long,
    val productList: List<ProductQuantity>,
    val deliveryInfo: DeliveryInfo,
)  {
    fun toOutbound(locationIdList: List<Long>, order: Order): Outbound {
        return Outbound(
            order = order,
            isPriorityDelivery = isPriorityDelivery,
            desiredDeliveryDateTime = desiredDeliveryDate,
            pickingLocationIdList = locationIdList,
            outboundStatus = OutboundStatus.CREATED
        )
    }
}