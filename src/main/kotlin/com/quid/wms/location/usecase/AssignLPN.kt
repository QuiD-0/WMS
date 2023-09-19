package com.quid.wms.location.usecase

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.LPNRepository
import com.quid.wms.location.gateway.repository.LocationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface AssignLPN {

    fun assign(lpnBarcode: String, locationBarcode: String): Location

    @Service
    @Transactional
    class AssignLPNUseCase(
        private val locationRepository: LocationRepository,
        private val lpnRepository: LPNRepository
    ) : AssignLPN {

        override fun assign(lpnBarcode: String, locationBarcode: String): Location {
            val location = locationRepository.findByBarcode(locationBarcode)
            val lpn = lpnRepository.findByBarcode(lpnBarcode)

            return location.assignLPN(lpn)
                .let { locationRepository.save(it) }
        }
    }
}