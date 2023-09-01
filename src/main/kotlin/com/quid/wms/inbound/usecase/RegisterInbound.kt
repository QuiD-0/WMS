package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.inbound.gateway.repository.InboundItemRepository
import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import com.quid.wms.product.gateway.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface RegisterInbound {

    fun register(request: RegistInboundRequest)

    @Service
    @Transactional
    class RegisterInboundUseCase(
        private val productRepository: ProductRepository,
        private val inboundRepository: InboundRepository,
        private val inboundItemRepository: InboundItemRepository
    ) : RegisterInbound {
        override fun register(request: RegistInboundRequest) {
            request.item.map { productRepository.findById(it.productId)
                    .let { product -> it.toInboundItem(product) } }
                .let { inboundItemRepository.saveAll(it) }
                .also { inboundRepository.save(request.toInbound(it)) }
        }
    }
}