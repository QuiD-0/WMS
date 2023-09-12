package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.LPN
import com.quid.wms.inbound.gateway.repository.jpa.LPNJpaRepository
import org.springframework.stereotype.Repository

interface LPNRepository {
    fun findByBarcode(lpnBarcode: String): LPN

    @Repository
    class LPNRepositoryImpl(
        private val jpaRepository: LPNJpaRepository
    ): LPNRepository {
        override fun findByBarcode(lpnBarcode: String): LPN {
            return jpaRepository.findByLpnBarcode(lpnBarcode)?.toLPN() ?: throw Exception("LPN not found")
        }

    }
}