package com.quid.wms.order.usecase

import com.quid.wms.fixture.OrderFixture
import com.quid.wms.fixture.ProductFixture
import com.quid.wms.mock.UserRepository
import com.quid.wms.order.gateway.web.reqeust.CreateOrderRequest
import com.quid.wms.outbound.domain.DeliveryInfo
import com.quid.wms.outbound.usecase.CreateOrder
import org.junit.jupiter.api.Test

class CreateOrderTest {

    @Test
    fun createOrder() {
        val orderRepository = OrderFixture().repository()
        val productRepository = ProductFixture().repository()
        val userMock = UserRepository.UserMock()
        val createOrder = CreateOrder.CreateOrderUseCase(orderRepository, productRepository, userMock)

        productRepository.save(ProductFixture().product())

        val order = createOrder.execute(
            CreateOrderRequest(
                userId = 1L,
                productList = mapOf(1L to 1),
                deliveryInfo = DeliveryInfo(
                    address = "address",
                    zipCode = "zipCode",
                    memo = "memo",
                )
            )
        )
        assert(order == 1L)
    }
}