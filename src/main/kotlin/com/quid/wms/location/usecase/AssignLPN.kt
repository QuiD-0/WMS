package com.quid.wms.location.usecase

import com.quid.wms.inbound.gateway.repository.LPNRepository
import com.quid.wms.location.domain.Location
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

        override fun assign(lpnBarcode: String, locationBarcode: String): Location =
            Pair(locationRepository.findByBarcode(locationBarcode), lpnRepository.findByBarcode(lpnBarcode))
            .let { it.first.assignLPN(it.second) }
            .let { locationRepository.save(it) }
    }
}