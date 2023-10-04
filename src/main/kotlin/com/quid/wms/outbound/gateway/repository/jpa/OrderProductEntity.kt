package com.quid.wms.outbound.gateway.repository.jpa

import com.quid.wms.outbound.domain.OrderProduct
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.Comment

@Entity
@Table(name = "order_product")
@Comment("주문 상품")
class OrderProductEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_product_id")
    var id: Long? = null,
    val productId: Long,
    val quantity: Long,
    val unitPrice: Int,
) {
    fun toOrderProduct(): OrderProduct = OrderProduct(
        id = id,
        productId = productId,
        quantity = quantity,
        unitPrice = unitPrice,
    )
}

fun orderProductEntity(orderProduct: OrderProduct) = OrderProductEntity(
    id = orderProduct.id,
    productId = orderProduct.productId,
    quantity = orderProduct.quantity,
    unitPrice = orderProduct.unitPrice,
)