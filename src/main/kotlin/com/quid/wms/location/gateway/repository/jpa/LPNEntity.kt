package com.quid.wms.location.gateway.repository.jpa

import com.quid.wms.location.domain.LPN
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "lpn",
    indexes = [Index(name = "lpn_barcode_idx", columnList = "lpnBarcode", unique = true)])
@Comment("LPN")
class LPNEntity(
    @Id
    @Column(name = "lpn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productId: Long,
    val inboundItemId: Long,
    val lpnBarcode: String,
    val expirationAt: LocalDateTime,
) {
    fun toLPN() = LPN(id, productId,inboundItemId, lpnBarcode, expirationAt)
}

fun lpnEntity(lpn: LPN) = LPNEntity(
    lpn.id,
    lpn.productId,
    lpn.inboundItemId,
    lpn.lpnBarcode,
    lpn.expirationAt
)