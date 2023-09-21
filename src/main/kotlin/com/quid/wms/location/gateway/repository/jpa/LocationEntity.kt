package com.quid.wms.location.gateway.repository.jpa

import com.quid.wms.location.domain.Location
import com.quid.wms.location.domain.StorageType
import com.quid.wms.location.domain.UsagePurpose
import jakarta.persistence.*
import jakarta.persistence.CascadeType.MERGE
import jakarta.persistence.CascadeType.PERSIST
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.Comment

@Entity
@Comment("위치")
@Table(name = "location",
    indexes = [Index(name = "location_barcode_idx", columnList = "locationBarcode", unique = true)])
class LocationEntity(
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = IDENTITY)
    val id: Long?,
    val locationBarcode: String,
    @Enumerated(STRING)
    val storageType: StorageType,
    @Enumerated(STRING)
    val usagePurpose: UsagePurpose,
    @OneToMany(cascade = [PERSIST, MERGE])
    @JoinColumn(name = "location_id")
    val lpnList: List<LPNEntity>,
) {
    fun toLocation() = Location(id, locationBarcode, storageType, usagePurpose, lpnList.map { it.toLPN() })
}

fun locationEntity(location: Location) = LocationEntity(
    location.id, location.locationBarcode, location.storageType, location.usagePurpose, location.lpnList.map { lpnEntity(it) }
)