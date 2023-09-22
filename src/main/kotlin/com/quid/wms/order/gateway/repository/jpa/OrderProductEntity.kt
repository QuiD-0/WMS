package com.quid.wms.order.gateway.repository.jpa

import com.quid.wms.order.domain.OrderProduct
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Table(name = "order_product")
@Comment("주문 상품")
class OrderProductEntity(
    @Id
    @Column(name = "order_product_id")
    var id: Long? = null,
    @Column(name = "product_id")
    val productId: Long,
    @Column(name = "quantity")
    val quantity: Int,
    @Column(name = "unit_price")
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