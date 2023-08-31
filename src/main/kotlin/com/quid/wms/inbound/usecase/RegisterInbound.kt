package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.inbound.gateway.repository.InboundItemRepository
import com.quid.wms.inbound.gateway.web.request.RegisterInboundRequest
import org.springframework.stereotype.Service

interface RegisterInbound {

    fun register(request: RegisterInboundRequest)

    @Service
    class RegisterInboundUseCase(
        private val inboundRepository: InboundRepository,
        private val inboundItemRepository: InboundItemRepository
    ) : RegisterInbound {
        override fun register(request: RegisterInboundRequest) {
            request.item.map { inboundItemRepository.findById(it) }
                .also { inboundRepository.save(request.toDomain(it)) }
        }
    }
}