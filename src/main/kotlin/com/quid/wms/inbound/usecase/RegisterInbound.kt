package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.inbound.gateway.repository.ItemRepository
import com.quid.wms.inbound.gateway.web.request.RegisterInboundRequest
import org.springframework.stereotype.Service

interface RegisterInbound {

    fun register(request: RegisterInboundRequest)

    @Service
    class RegisterInboundUseCase(
        private val inboundRepository: InboundRepository,
        private val itemRepository: ItemRepository
    ): RegisterInbound {
        override fun register(request: RegisterInboundRequest) {
            val items = request.item.map { itemRepository.findById(it) }
            inboundRepository.save(Inbound(null, request.title, request.description, request.orderRequestAt, request.estimateArrivalAt, items))
        }
    }
}