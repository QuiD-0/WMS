package com.quid.wms.inbound.gateway.repository.jpa

import com.quid.wms.inbound.domain.InboundItem
import com.quid.wms.product.gateway.repository.jpa.ProductEntity
import com.quid.wms.product.gateway.repository.jpa.productEntity
import jakarta.persistence.*
import jakarta.persistence.CascadeType.MERGE
import jakarta.persistence.CascadeType.PERSIST
import org.hibernate.annotations.Comment

@Entity
@Comment("입고 상품")
@Table(name = "inbound_item")
class InboundItemEntity(
    @Id
    @Column(name = "inbound_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: ProductEntity,
    val quantity: Long,
    val unitPrice: Long,
    @OneToMany(cascade = [PERSIST, MERGE])
    val lpnList: List<LPNEntity>
) {
    fun toInboundItem() = InboundItem(id, product.toProduct(), quantity, unitPrice, lpnList.map { it.toLPN() })
}

fun inboundItemEntity(inboundItem: InboundItem) = InboundItemEntity(
    inboundItem.id,
    productEntity(inboundItem.product),
    inboundItem.quantity,
    inboundItem.unitPrice,
    inboundItem.lpnList.map { lpnEntity(it) }
)