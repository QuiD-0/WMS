package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.gateway.repository.InboundItemRepository
import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface RegisterInbound {

    fun register(request: RegistInboundRequest): Inbound

    @Service
    @Transactional
    class RegisterInboundUseCase(
        private val inboundRepository: InboundRepository,
        private val inboundItemRepository: InboundItemRepository,
    ) : RegisterInbound {
        override fun register(request: RegistInboundRequest) = request.toInbound()
            .let { inboundRepository.save(it) }
            .also { e -> inboundItemRepository.saveAll(request.inboundItems.map { it.toInboundItem(e.id!!) }) }
    }
}