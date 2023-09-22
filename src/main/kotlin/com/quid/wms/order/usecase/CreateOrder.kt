package com.quid.wms.order.usecase

import com.quid.wms.mock.UserRepository
import com.quid.wms.order.domain.OrderCustomer
import com.quid.wms.order.domain.OrderProduct
import com.quid.wms.order.domain.createOrder
import com.quid.wms.order.gateway.repository.OrderRepository
import com.quid.wms.order.gateway.web.reqeust.CreateOrderRequest
import com.quid.wms.product.gateway.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface CreateOrder {
    fun execute(request: CreateOrderRequest): Long

    @Service
    @Transactional
    class CreateOrderUseCase(
        private val orderRepository: OrderRepository,
        private val productRepository: ProductRepository,
        private val userRepository: UserRepository,
    ) : CreateOrder {
        override fun execute(request: CreateOrderRequest): Long {
            val customer: OrderCustomer = userRepository.findUserById(request.userId).toOrderCustomer()
            val productList: List<OrderProduct> = productRepository.findOrderProducts(request.productQuantityList)

            return createOrder(customer, request.deliveryInfo, productList)
                .let { orderRepository.save(it) }
        }
    }
}