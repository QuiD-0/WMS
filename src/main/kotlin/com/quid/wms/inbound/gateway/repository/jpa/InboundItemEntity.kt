package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.InboundItem
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Comment("입고 상품")
@Table(name = "inbound_item")
class InboundItemEntity(
    @Id
    @Column(name = "inbound_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productId: Long,
    val quantity: Long,
    val unitPrice: Long,
    val inboundId: Long,
) {
    fun toInboundItem() = InboundItem(id, productId, quantity, unitPrice,inboundId)
}

fun inboundItemEntity(inboundItem: InboundItem) = InboundItemEntity(
    inboundItem.id,
    inboundItem.productId,
    inboundItem.quantity,
    inboundItem.unitPrice,
    inboundItem.inboundId,
)