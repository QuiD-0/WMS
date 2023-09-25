package com.quid.wms.init

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.domain.InboundItem
import com.quid.wms.inbound.domain.InboundStatus
import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.location.domain.LPN
import com.quid.wms.location.domain.Location
import com.quid.wms.location.domain.StorageType
import com.quid.wms.location.domain.UsagePurpose
import com.quid.wms.location.gateway.repository.LocationRepository
import com.quid.wms.order.domain.DeliveryInfo
import com.quid.wms.order.domain.Order
import com.quid.wms.order.domain.OrderCustomer
import com.quid.wms.order.domain.OrderProduct
import com.quid.wms.order.gateway.repository.OrderRepository
import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.Product
import com.quid.wms.product.domain.ProductSize
import com.quid.wms.product.domain.TemperatureZone
import com.quid.wms.product.gateway.repository.ProductRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

//@Disabled
@SpringBootTest
class InitDataScript {

    @Autowired
    private lateinit var product: ProductRepository

    @Autowired
    private lateinit var inbound: InboundRepository

    @Autowired
    private lateinit var location: LocationRepository

    @Autowired
    private lateinit var order: OrderRepository

    @Test
    fun initDataScript() {
        product.save(product(1))
        product.save(product(2))
        inbound.save(inbound())
        location.save(location1())
        location.save(location2())
        order.save(order())
    }

    fun product(productId: Long): Product = Product(
        id = productId,
        name = "Product $productId",
        code = "P$productId",
        price = 1000,
        description = "Product $productId description",
        brand = "Brand $productId",
        maker = "Maker $productId",
        origin = "Origin $productId",
        category = Category.FOOD,
        temperatureZone = TemperatureZone.ROOM_TEMPERATURE,
        weight = 100L,
        productSize = ProductSize(10, 10, 10),
    )


    fun inbound() = Inbound(
        id = 1L,
        title = "Inbound 1",
        description = "Inbound 1 description",
        orderRequestAt = LocalDateTime.now(),
        estimateArrivalAt = LocalDateTime.now().plusDays(3),
        inboundItems = listOf(
            InboundItem(
                id = 1L,
                product = product(1),
                quantity = 10,
                unitPrice = 1000,
            ),
            InboundItem(
                id = 2L,
                product = product(2),
                quantity = 10,
                unitPrice = 1000,
                ),
        ),
        status = InboundStatus.REQUESTED,
        rejectReason = ""
    )

    fun location1(): Location = Location(
        id = 1L,
        locationBarcode = "Location-1",
        storageType = StorageType.PALLET,
        usagePurpose = UsagePurpose.MOVE,
        lpnList = listOf(lpn(1,1),lpn(2,2)),
    )
    fun location2(): Location = Location(
        id = 2L,
        locationBarcode = "Location-2",
        storageType = StorageType.PALLET,
        usagePurpose = UsagePurpose.MOVE,
        lpnList = listOf(lpn(1,1),lpn(3,3)),
    )

    fun lpn(id: Long,productId: Long):LPN = LPN(
        id = id,
        productId = productId,
        inboundItemId = 1L,
        lpnBarcode = "LPN-$id",
        expirationAt = LocalDateTime.now().plusMonths(1),
        quantity = 10L,
    )

    fun order(): Order = Order(
        id = 1L,
        orderCustomer = OrderCustomer(
            name = "Customer 1",
            phone = "010-1234-5678",
            email = "customer@mail.com"
        ),
        deliveryInfo = DeliveryInfo(
            address = "Address 1",
            zipCode = "12345",
            memo = "Memo 1",
        ),
        orderProducts = listOf(
            orderProduct()
        ),
    )

    fun orderProduct(): OrderProduct = OrderProduct(
        id = 1L,
        productId = 1L,
        quantity = 10,
        unitPrice = 1000,
    )
}
