package com.quid.wms.outbound.usecase

import com.quid.wms.location.domain.LPN
import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.repository.LocationRepository
import com.quid.wms.outbound.domain.Order
import com.quid.wms.outbound.domain.Outbound
import com.quid.wms.outbound.domain.ProductQuantity
import com.quid.wms.outbound.gateway.repository.OutboundRepository
import com.quid.wms.outbound.gateway.web.request.RegisterOutboundRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface RegisterOutbound {

    fun registerOutbound(request: RegisterOutboundRequest): Outbound

    @Service
    @Transactional
    class RegisterOutboundUseCase(
        private val createOrder: CreateOrder,
        private val locationRepository: LocationRepository,
        private val outboundRepository: OutboundRepository,
    ) : RegisterOutbound {
        override fun registerOutbound(request: RegisterOutboundRequest): Outbound {
            val order: Order = createOrder.execute(request.userId, request.productList, request.deliveryInfo)
            val locations = locationRepository.findLocationList(order.findProductIdList())
            val productQuantity: List<ProductQuantity> = Location.getStockQuantity(locations)

            checkQuantity(order, productQuantity)
            val lpnList: List<LPN> = allocateLocation(order, locations, productQuantity)

            return request.toOutbound(lpnList.map { it.id!! }, order)
                .let { outboundRepository.save(it) }
        }

        private fun checkQuantity(order: Order, productQuantity: List<ProductQuantity>) {
            val orderProduct = order.orderProducts

            orderProduct.forEach { product ->
                val quantity = productQuantity.find { it.productId == product.productId }?.quantity?: 0
                if (product.quantity > quantity) {
                    throw RuntimeException("재고가 부족합니다.")
                }
            }
        }

        private fun allocateLocation(order: Order, locations :List<Location>, productQuantity: List<ProductQuantity>): List<LPN> {
            val lpnList: MutableList<LPN> = mutableListOf()
            val orderProduct = order.orderProducts

            orderProduct.forEach { product ->
                var quantity = productQuantity.find { it.productId == product.productId }?.quantity ?: 0
                locations.forEach { location ->
                    if (location.hasProduct(product.productId)) {
                        val lpn = location.getLpn(product.productId)
                        lpnList.add(lpn)
                        location.updateAmount(lpn, quantity - product.quantity)
                            .let { locationRepository.save(it) }
                        quantity -= product.quantity
                    }
                }
            }
            return lpnList
        }

    }
}