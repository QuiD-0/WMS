package com.quid.wms.outbound.gateway.repository.jpa

import com.quid.wms.order.gateway.repository.jpa.OrderEntity
import com.quid.wms.order.gateway.repository.jpa.orderEntity
import com.quid.wms.outbound.domain.Outbound
import com.quid.wms.outbound.domain.OutboundStatus
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY
import java.time.LocalDate

@Entity
@Table(name = "outbound")
class OutboundEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: OrderEntity,
    val isPriorityDelivery: Boolean,
    val desiredDeliveryDateTime: LocalDate,
    @ElementCollection
    val pickingLocationIdList: List<Long>,
    @Enumerated(STRING)
    val outboundStatus: OutboundStatus,
) {
    fun toOutbound(): Outbound = Outbound(
        id = id,
        order = order.toOrder(),
        isPriorityDelivery = isPriorityDelivery,
        desiredDeliveryDateTime = desiredDeliveryDateTime,
        pickingLocationIdList = pickingLocationIdList,
        outboundStatus = outboundStatus,
    )
}

fun outboundEntity(outbound: Outbound): OutboundEntity {
    return OutboundEntity(
        id = outbound.id,
        order = orderEntity(outbound.order),
        isPriorityDelivery = outbound.isPriorityDelivery,
        desiredDeliveryDateTime = outbound.desiredDeliveryDateTime,
        pickingLocationIdList = outbound.pickingLocationIdList,
        outboundStatus = outbound.outboundStatus,
    )
}