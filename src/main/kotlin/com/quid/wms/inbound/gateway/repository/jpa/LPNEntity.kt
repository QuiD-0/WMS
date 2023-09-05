package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.LPN
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "lpn")
@Comment("LPN")
class LPNEntity(
    @Id
    @Column(name = "lpn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val lpnBarcode: String,
    val expirationAt: LocalDateTime,
) {
    fun toLPN() = LPN(id, lpnBarcode, expirationAt)

    override fun toString(): String {
        return "LPNEntity(id=$id, lpnBarcode='$lpnBarcode', expirationAt=$expirationAt)"
    }

}

fun lpnEntity(lpn: LPN) = LPNEntity(
    lpn.id,
    lpn.lpnBarcode,
    lpn.expirationAt
)