package com.quid.wms.location.gateway.repository

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.jpa.LocationJpaRepository
import com.quid.wms.location.gateway.repository.jpa.locationEntity
import org.springframework.stereotype.Repository

interface LocationRepository {
    fun save(location: Location): Location

    @Repository
    class LocationRepositoryImpl(
        private val locationRepository: LocationJpaRepository
    ): LocationRepository {
        override fun save(location: Location): Location = locationRepository.save(locationEntity(location)).toLocation()
    }
}