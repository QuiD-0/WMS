package com.quid.wms.location.gateway.repository

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.jpa.LocationJpaRepository
import com.quid.wms.location.gateway.repository.jpa.locationEntity
import org.springframework.stereotype.Repository

interface LocationRepository {
    fun save(location: Location): Long
    fun findByBarcode(locationBarcode: String): Location
    fun findAll(): List<Location>

    @Repository
    class LocationRepositoryImpl(
        private val jpaRepository: LocationJpaRepository
    ): LocationRepository {
        override fun save(location: Location): Long = jpaRepository.save(locationEntity(location)).id!!
        override fun findByBarcode(locationBarcode: String): Location = jpaRepository.findByLocationBarcode(locationBarcode)?.toLocation() ?: throw Exception("Location not found")
        override fun findAll(): List<Location> = jpaRepository.findAll().map { it.toLocation() }
    }
}