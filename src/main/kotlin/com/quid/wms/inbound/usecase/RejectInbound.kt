package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.gateway.repository.InboundRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface RejectInbound {

        fun execute(inboundId: Long, rejectMessage: String)

        @Service
        @Transactional
        class RejectInboundImpl(private val inboundRepository: InboundRepository) : RejectInbound {
            override fun execute(inboundId: Long, rejectMessage: String) {
                inboundRepository.findById(inboundId).reject(rejectMessage)
                    .also { inboundRepository.save(it) }
            }
        }
}