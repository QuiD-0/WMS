package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.domain.LPN
import com.quid.wms.inbound.gateway.repository.LPNRepository
import com.quid.wms.inbound.gateway.web.request.RegisterLPNRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface RegisterLPN {

    fun execute(request: RegisterLPNRequest): LPN

    @Service
    @Transactional
    class RegisterLPNImpl(
        private val lpnRepository: LPNRepository,
    ) : RegisterLPN {
        override fun execute(request: RegisterLPNRequest): LPN = request
            .toLPN().let {
                lpnRepository.save(it)
            }
    }
}