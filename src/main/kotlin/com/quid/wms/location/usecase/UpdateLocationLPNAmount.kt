package com.quid.wms.location.usecase

import com.quid.wms.inbound.gateway.repository.LPNRepository
import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.LocationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface UpdateLocationLPNAmount {
    fun modify(locationBarcode: String, lpnBarcode: String, amount: Int): Location

    @Service
    @Transactional
    class UpdateLocationLPNAmountImpl(
        private val locationRepository: LocationRepository,
        private val lpnRepository: LPNRepository
    ) : UpdateLocationLPNAmount {
        override fun modify(locationBarcode: String, lpnBarcode: String, amount: Int): Location {
            val location = locationRepository.findByBarcode(locationBarcode)
            val lpn = lpnRepository.findByBarcode(lpnBarcode)

            return location.updateAmount(lpn, amount)
                .let { locationRepository.save(it) }
        }
    }
}