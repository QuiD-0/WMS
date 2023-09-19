package com.quid.wms.location.gateway.repository.jpa

import com.quid.wms.location.domain.LocationLPN
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.Comment

@Entity
@Table(name = "location_lpn")
@Comment("위치 LPN")
class LocationLPNEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "location_lpn_id")
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val lpn: LPNEntity,
    val quantity: Long,
){
    fun toLocationLPN() = LocationLPN(id, lpn.toLPN(), quantity)
}

fun locationLPNEntity(locationLPN: LocationLPN) = LocationLPNEntity(
    locationLPN.id,
    lpnEntity(locationLPN.lpn),
    locationLPN.quantity
)