package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.LPN
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "lpn",
    indexes = [Index(name = "lpn_barcode_idx", columnList = "lpn_barcode", unique = true)])
@Comment("LPN")
class LPNEntity(
    @Id
    @Column(name = "lpn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productId: Long,
    val lpnBarcode: String,
    val expirationAt: LocalDateTime,
) {
    fun toLPN() = LPN(id, productId, lpnBarcode, expirationAt)
}

fun lpnEntity(lpn: LPN) = LPNEntity(
    lpn.id,
    lpn.productId,
    lpn.lpnBarcode,
    lpn.expirationAt
)