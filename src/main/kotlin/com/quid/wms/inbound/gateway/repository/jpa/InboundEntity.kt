package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.domain.InboundStatus
import jakarta.persistence.*
import jakarta.persistence.CascadeType.MERGE
import jakarta.persistence.CascadeType.PERSIST
import jakarta.persistence.EnumType.STRING
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
    @OneToMany(cascade = [PERSIST, MERGE])
    @JoinColumn(name = "inbound_id")
    val inboundItems: Set<InboundItemEntity>,
    @Enumerated(STRING)
    val status: InboundStatus,
    val rejectReason: String,
) {
    fun toInbound() = Inbound(id, title, description, orderRequestAt, estimateArrivalAt, inboundItems.map { it.toInboundItem() }, status, rejectReason)
}

fun inboundEntity(inbound: Inbound) = InboundEntity(
    inbound.id,
    inbound.title,
    inbound.description,
    inbound.orderRequestAt,
    inbound.estimateArrivalAt,
    inbound.inboundItems.map { inboundItemEntity(it) }.toSet(),
    inbound.status,
    inbound.rejectReason
)