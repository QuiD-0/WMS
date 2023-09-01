package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.InboundItem
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "inbound_item")
@Comment("입고 상품")
class InboundItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productId: Long,
    val quantity: Long,
    val unitPrice: Long
) {
    fun toInboundItem() = InboundItem(id, productId, quantity, unitPrice)
}

fun inboundItemEntity(inboundItem: InboundItem) = InboundItemEntity(
    inboundItem.id,
    inboundItem.productId,
    inboundItem.quantity,
    inboundItem.unitPrice
)