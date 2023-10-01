package com.quid.wms.outbound.gateway.repository

import com.quid.wms.outbound.domain.Outbound
import com.quid.wms.outbound.gateway.repository.jpa.OutboundJpaRepository
import com.quid.wms.outbound.gateway.repository.jpa.outboundEntity
import org.springframework.stereotype.Repository

interface OutboundRepository {
    fun save(outbound: Outbound) : Outbound

    @Repository
    class OutboundRepositoryImpl(
        private val outboundJpaRepository: OutboundJpaRepository,
    ) : OutboundRepository {
        override fun save(outbound: Outbound): Outbound = outboundJpaRepository.save(outboundEntity(outbound)).toOutbound()
    }
}