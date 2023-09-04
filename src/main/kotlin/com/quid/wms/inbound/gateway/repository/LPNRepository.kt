package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.gateway.repository.jpa.LPNJpaRepository
import org.springframework.stereotype.Repository

interface LPNRepository {

    @Repository
    class LPNRepositoryImpl(
        private val jpaRepository: LPNJpaRepository
    ) : LPNRepository {

    }
}