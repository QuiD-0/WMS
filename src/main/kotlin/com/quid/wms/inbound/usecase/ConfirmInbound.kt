package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.gateway.repository.InboundRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ConfirmInbound {

    fun execute(inboundId: Long)

    @Service
    @Transactional
    class ConfirmInboundUseCase(
        private val inboundRepository: InboundRepository
    ): ConfirmInbound {

        override fun execute(inboundId: Long) {
            inboundRepository.findById(inboundId)
                .let { inboundRepository.save(it.confirm()) }
        }

    }
}