package com.quid.wms.location.usecase

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.LocationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FindLocation {
    fun byBarcode(barcode: String): Location
    fun findAll(): List<Location>

    @Service
    @Transactional(readOnly = true)
    class FindLocationUseCase(
        private val repository: LocationRepository
    ): FindLocation {
        override fun byBarcode(barcode: String): Location = repository.findByBarcode(barcode)
        override fun findAll(): List<Location> = repository.findAll()

    }
}