package com.quid.wms.outbound.gateway.repository

import com.quid.wms.outbound.domain.Outbound
import org.springframework.stereotype.Repository

interface OutboundRepository {
    fun save(outbound: Outbound) : Long

    @Repository
    class OutboundRepositoryImpl : OutboundRepository {
        override fun save(outbound: Outbound): Long {
            return 1L
        }
    }
}