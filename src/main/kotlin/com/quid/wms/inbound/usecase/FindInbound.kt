package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.gateway.repository.InboundRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FindInbound {
    fun byId(id: Long): Inbound
    fun all(): List<Inbound>

    @Service
    @Transactional(readOnly = true)
    class FindInboundUseCase(
        private val inboundRepository: InboundRepository
    ) : FindInbound {
        override fun byId(id: Long): Inbound = inboundRepository.findById(id)
        override fun all(): List<Inbound> = inboundRepository.findAll()
    }

}