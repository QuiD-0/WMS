package com.quid.wms.location.usecase

import com.quid.wms.inbound.gateway.repository.InboundItemRepository
import com.quid.wms.location.gateway.repository.LPNRepository
import com.quid.wms.location.gateway.web.request.RegisterLPNRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface RegisterLPN {

    fun execute(registerLPNRequest: RegisterLPNRequest): Long

    @Service
    @Transactional
    class RegisterLPNImpl(
        private val inboundItemRepository: InboundItemRepository,
        private val lpnRepository: LPNRepository
    ) : RegisterLPN {
        override fun execute(registerLPNRequest: RegisterLPNRequest): Long {
            val inboundItem = inboundItemRepository.findById(registerLPNRequest.inboundItemId)
            return registerLPNRequest.toLPN(inboundItem.product.id!!)
                .let { lpnRepository.save(it) }
        }
    }
}