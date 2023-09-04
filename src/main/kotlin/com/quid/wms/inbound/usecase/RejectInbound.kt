package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.gateway.repository.InboundRepository
import org.springframework.stereotype.Service

fun interface RejectInbound {

        fun execute(inboundId: Long)

        @Service
        class RejectInboundImpl(private val inboundRepository: InboundRepository) : RejectInbound {
            override fun execute(inboundId: Long) {
                inboundRepository.findById(inboundId).reject()
                    .also { inboundRepository.save(it) }
            }
        }
}