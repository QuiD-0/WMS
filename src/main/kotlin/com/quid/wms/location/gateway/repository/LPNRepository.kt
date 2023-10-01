package com.quid.wms.location.gateway.repository

import com.quid.wms.location.domain.LPN
import com.quid.wms.location.gateway.repository.jpa.LPNJpaRepository
import com.quid.wms.location.gateway.repository.jpa.lpnEntity
import org.springframework.stereotype.Repository

interface LPNRepository {
    fun save(lpn: LPN): LPN
    fun findByBarcode(lpnBarcode: String): LPN

    @Repository
    class LPNRepositoryImpl(
        private val jpaRepository: LPNJpaRepository
    ): LPNRepository {
        override fun save(lpn: LPN): LPN {
            return jpaRepository.save(lpnEntity(lpn)).toLPN()
        }

        override fun findByBarcode(lpnBarcode: String): LPN {
            return jpaRepository.findByLpnBarcode(lpnBarcode)?.toLPN() ?: throw Exception("LPN not found")
        }

    }
}