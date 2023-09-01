package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.Inbound
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "inbound")
@Comment("입고")
class InboundEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val description: String,
    val orderRequestAt: LocalDateTime,
    val estimateArrivalAt: LocalDateTime,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val inboundItems: List<InboundItemEntity>
) {
    fun toInbound() = Inbound(id, title, description, orderRequestAt, estimateArrivalAt, inboundItems.map { it.toInboundItem() })
}

fun inboundEntity(inbound: Inbound) = InboundEntity(
    inbound.id,
    inbound.title,
    inbound.description,
    inbound.orderRequestAt,
    inbound.estimateArrivalAt,
    inbound.inboundItems.map { inboundItemEntity(it) }
)