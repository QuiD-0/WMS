package com.quid.wms.outbound.usecase

import com.quid.wms.order.gateway.repository.OrderRepository
import com.quid.wms.outbound.gateway.web.request.RegisterOutboundRequest
import org.springframework.stereotype.Service

fun interface RegisterOutbound {

    fun registerOutbound(request: RegisterOutboundRequest): Long

    @Service
    class RegisterOutboundUseCase(
        private val orderRepository: OrderRepository,
    ) : RegisterOutbound {
        override fun registerOutbound(request: RegisterOutboundRequest): Long {
            val order = orderRepository.findById(request.orderId)
            order.checkQuantity()

            return Any() as Long
        }
    }

}