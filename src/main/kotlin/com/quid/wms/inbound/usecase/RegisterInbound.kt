package com.quid.wms.inbound.usecase

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import com.quid.wms.product.gateway.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface RegisterInbound {

    fun register(request: RegistInboundRequest): Inbound

    @Service
    @Transactional
    class RegisterInboundUseCase(
        private val productRepository: ProductRepository,
        private val inboundRepository: InboundRepository
    ) : RegisterInbound {
        override fun register(request: RegistInboundRequest) = with(request) {
            this.item.map { productRepository.findById(it.productId)
                .let { product -> it.toInboundItem(product) } }
                .let { inboundRepository.save(this.toInbound(it)) }
        }
    }
}