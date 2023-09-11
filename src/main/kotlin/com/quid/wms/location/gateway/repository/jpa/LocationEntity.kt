package com.quid.wms.location.gateway.repository.jpa

import com.quid.wms.location.domain.Location
import com.quid.wms.location.domain.StorageType
import com.quid.wms.location.domain.UsagePurpose
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.Comment

@Entity
@Comment("위치")
@Table(name = "location")
class LocationEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long?,
    val locationBarcode: String,
    @Enumerated(STRING)
    val storageType: StorageType,
    @Enumerated(STRING)
    val usagePurpose: UsagePurpose
) {
    fun toLocation() = Location(id, locationBarcode, storageType, usagePurpose)
}

fun locationEntity(location: Location) = LocationEntity(
    location.id, location.locationBarcode, location.storageType, location.usagePurpose
)