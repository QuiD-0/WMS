package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.Inbound
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Comment("입고")
@Table(name = "inbound")
class InboundEntity(
    @Id
    @Column(name = "inbound_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val description: String,
    val orderRequestAt: LocalDateTime,
    val estimateArrivalAt: LocalDateTime,
    @OneToMany(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "inbound_id")
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