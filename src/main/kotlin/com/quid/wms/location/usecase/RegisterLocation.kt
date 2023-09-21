package com.quid.wms.location.usecase

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.LocationRepository
import org.springframework.stereotype.Service

interface RegisterLocation {

    fun execute(location: Location): Long

    @Service
    class RegisterLocationUseCase(
        private val repository: LocationRepository
    ): RegisterLocation{
        override fun execute(location: Location): Long = repository.save(location)
    }
}