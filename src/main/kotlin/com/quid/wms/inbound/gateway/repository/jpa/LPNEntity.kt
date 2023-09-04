package com.quid.wms.inbound.gateway.repository.jpa

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "lpn")
@Comment("LPN")
class LPNEntity(
    @Id
    @Column(name = "lpn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val lpnBarcode: String,
    val expirationAt: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inbound_item_id")
    val inboundItem: InboundItemEntity
) {
}