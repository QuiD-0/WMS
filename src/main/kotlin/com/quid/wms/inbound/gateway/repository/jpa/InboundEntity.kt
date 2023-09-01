package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.domain.InboundStatus
import jakarta.persistence.*
import jakarta.persistence.EnumType.*
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
    val inboundItems: List<InboundItemEntity>,
    @Enumerated(STRING)
    val status: InboundStatus
) {
    fun toInbound() = Inbound(id, title, description, orderRequestAt, estimateArrivalAt, inboundItems.map { it.toInboundItem() }, status)
}

fun inboundEntity(inbound: Inbound) = InboundEntity(
    inbound.id,
    inbound.title,
    inbound.description,
    inbound.orderRequestAt,
    inbound.estimateArrivalAt,
    inbound.inboundItems.map { inboundItemEntity(it) },
    inbound.status
)