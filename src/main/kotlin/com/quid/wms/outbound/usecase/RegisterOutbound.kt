package com.quid.wms.outbound.usecase

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.LocationRepository
import com.quid.wms.order.domain.Order
import com.quid.wms.order.gateway.repository.OrderRepository
import com.quid.wms.outbound.gateway.repository.OutboundRepository
import com.quid.wms.outbound.gateway.web.request.RegisterOutboundRequest
import org.springframework.stereotype.Service

fun interface RegisterOutbound {

    fun registerOutbound(request: RegisterOutboundRequest): Long

    @Service
    class RegisterOutboundUseCase(
        private val orderRepository: OrderRepository,
        private val locationRepository: LocationRepository,
        private val outboundRepository: OutboundRepository
    ) : RegisterOutbound {
        override fun registerOutbound(request: RegisterOutboundRequest): Long {
            val order: Order = orderRepository.findById(request.orderId)
            val locationList: List<Location> = locationRepository.findLocationList(order.findProductIdList())

            return request.toOutbound(listOf(1L))
                .let { outboundRepository.save(it) }
        }
    }

}