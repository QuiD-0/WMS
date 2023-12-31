package com.quid.wms.location.gateway.repository

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.jpa.LocationJpaRepository
import com.quid.wms.location.gateway.repository.jpa.locationEntity
import org.springframework.stereotype.Repository

interface LocationRepository {
    fun save(location: Location): Location
    fun findByBarcode(locationBarcode: String): Location
    fun findAll(): List<Location>
    fun findLocationList(findProductIdList: List<Long>): List<Location>

    @Repository
    class LocationRepositoryImpl(
        private val jpaRepository: LocationJpaRepository
    ): LocationRepository {
        override fun save(location: Location): Location = jpaRepository.save(locationEntity(location)).toLocation()
        override fun findByBarcode(locationBarcode: String): Location = jpaRepository.findByLocationBarcode(locationBarcode)?.toLocation() ?: throw Exception("Location not found")
        override fun findAll(): List<Location> = jpaRepository.findAll().map { it.toLocation() }
        override fun findLocationList(findProductIdList: List<Long>): List<Location> = jpaRepository.findLocationList(findProductIdList).map { it.toLocation() }
    }
}