package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.gateway.repository.InboundItemRepository
import com.quid.wms.inbound.gateway.web.request.RegisterLPNRequest
import org.springframework.stereotype.Service

fun interface RegisterLPN {

    fun execute(registerLPNRequest: RegisterLPNRequest)

    @Service
    class RegisterLPNImpl(
        private val inboundItemRepository: InboundItemRepository,
    ) : RegisterLPN {
        override fun execute(registerLPNRequest: RegisterLPNRequest) {
            inboundItemRepository.findById(registerLPNRequest.inboundItemId)

        }
    }
}