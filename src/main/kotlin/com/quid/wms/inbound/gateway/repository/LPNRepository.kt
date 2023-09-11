package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.LPN
import com.quid.wms.inbound.gateway.repository.jpa.LPNJpaRepository
import com.quid.wms.inbound.gateway.repository.jpa.lpnEntity
import org.springframework.stereotype.Repository

interface LPNRepository {

    fun save(lpn: LPN): LPN

    @Repository
    class LPNRepositoryImpl(
        private val jpaRepository: LPNJpaRepository
    ) : LPNRepository {
        override fun save(lpn: LPN): LPN {
            return jpaRepository.save(lpnEntity(lpn)).toLPN()
        }
    }
}