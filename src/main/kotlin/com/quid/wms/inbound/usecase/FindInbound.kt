package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.gateway.repository.InboundRepository
import org.springframework.stereotype.Service

interface FindInbound{

    fun byId(id: Long) : Inbound

    @Service
    class FindInboundUseCase(
        private val inboundRepository: InboundRepository,
    ) : FindInbound{
        override fun byId(id: Long): Inbound {
            return inboundRepository.findById(id)
        }
    }

}