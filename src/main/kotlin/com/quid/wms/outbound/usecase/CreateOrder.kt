package com.quid.wms.outbound.usecase

import com.quid.wms.mock.UserRepository
import com.quid.wms.outbound.domain.*
import com.quid.wms.outbound.gateway.repository.OrderRepository
import com.quid.wms.product.gateway.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface CreateOrder {
    fun execute(userId: Long, productQuantity: List<ProductQuantity>, deliveryInfo: DeliveryInfo): Order

    @Service
    @Transactional
    class CreateOrderUseCase(
        private val orderRepository: OrderRepository,
        private val productRepository: ProductRepository,
        private val userRepository: UserRepository,
    ) : CreateOrder {
        override fun execute(userId: Long, productQuantity: List<ProductQuantity>, deliveryInfo: DeliveryInfo): Order {
            val customer: OrderCustomer = userRepository.findUserById(userId).toOrderCustomer()
            val productList: List<OrderProduct> = productRepository.findOrderProducts(productQuantity)

            return createOrder(customer, deliveryInfo, productList)
                .let { orderRepository.save(it) }
        }
    }
}