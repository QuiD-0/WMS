package com.quid.wms.outbound.gateway.repository.jpa

import com.quid.wms.outbound.domain.DeliveryInfo
import com.quid.wms.outbound.domain.Order
import com.quid.wms.outbound.domain.OrderCustomer
import jakarta.persistence.*
import jakarta.persistence.CascadeType.MERGE
import jakarta.persistence.CascadeType.PERSIST
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.Comment
import java.util.*

@Entity
@Table(name = "orders")
@Comment("주문")
class OrderEntity(
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val zipCode: String,
    val memo: String,
    val deliveryNumber: UUID,
    @OneToMany(cascade = [PERSIST, MERGE])
    @JoinColumn(name = "order_id")
    val orderProducts: List<OrderProductEntity>,
) {
    fun toOrder(): Order = Order(
        id = id,
        orderCustomer = OrderCustomer(
            name = name,
            email = email,
            phone = phone,
        ),
        deliveryInfo = DeliveryInfo(
            address = address,
            zipCode = zipCode,
            memo = memo,
            deliveryNumber = deliveryNumber,
        ),
        orderProducts = orderProducts.map { it.toOrderProduct() },
    )
}

fun orderEntity(order: Order) = OrderEntity(
    id = order.id,
    name = order.orderCustomer.name,
    email = order.orderCustomer.email,
    phone = order.orderCustomer.phone,
    address = order.deliveryInfo.address,
    zipCode = order.deliveryInfo.zipCode,
    memo = order.deliveryInfo.memo,
    deliveryNumber = order.deliveryInfo.deliveryNumber,
    orderProducts = order.orderProducts.map { orderProductEntity(it) },
)