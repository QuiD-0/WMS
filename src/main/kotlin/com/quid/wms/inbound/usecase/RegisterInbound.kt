package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.inbound.gateway.repository.InboundItemRepository
import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RegisterInbound {

    fun register(request: RegistInboundRequest)

    @Service
    @Transactional
    class RegisterInboundUseCase(
        private val inboundRepository: InboundRepository,
        private val inboundItemRepository: InboundItemRepository
    ) : RegisterInbound {
        override fun register(request: RegistInboundRequest) {
            request.item.map { it.toInboundItem() }
                .let { inboundItemRepository.saveAll(it) }
                .also { inboundRepository.save(request.toInbound(it)) }
        }
    }
}