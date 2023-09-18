package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.domain.InboundItem
import com.quid.wms.inbound.gateway.repository.InboundItemRepository
import com.quid.wms.inbound.gateway.web.request.RegisterLPNRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface RegisterLPN {

    fun execute(registerLPNRequest: RegisterLPNRequest): InboundItem

    @Service
    @Transactional
    class RegisterLPNImpl(
        private val inboundItemRepository: InboundItemRepository,
    ) : RegisterLPN {
        override fun execute(registerLPNRequest: RegisterLPNRequest): InboundItem {
            val inboundItem = inboundItemRepository.findById(registerLPNRequest.inboundItemId)
            val lpn = inboundItem.registerLPN(registerLPNRequest.toLPN(inboundItem.product.id!!))
            return inboundItemRepository.save(lpn)
        }
    }
}