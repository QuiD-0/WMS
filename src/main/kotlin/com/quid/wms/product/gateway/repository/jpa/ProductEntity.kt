package com.quid.wms.product.gateway.repository.jpa

import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.Product
import com.quid.wms.product.domain.ProductSize
import com.quid.wms.product.domain.TemperatureZone
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import org.hibernate.annotations.Comment

@Entity
@Comment("상품")
@Table(name = "product")
class ProductEntity(
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @Column(unique = true)
    val code: String,
    val price: Int,
    val description: String,
    val brand: String,
    val maker: String,
    val origin: String,
    @Enumerated(STRING)
    val category: Category,
    @Enumerated(STRING)
    val temperatureZone: TemperatureZone,
    val weight: Long,
    val widthInMillimeters: Long,
    val heightInMillimeters: Long,
    val lengthInMillimeters: Long,
) {
    fun toProduct() = Product(
        id,
        name,
        code,
        price,
        description,
        brand,
        maker,
        origin,
        category,
        temperatureZone,
        weight,
        ProductSize(
            widthInMillimeters,
            heightInMillimeters,
            lengthInMillimeters
        )
    )
}

fun productEntity(product: Product) = ProductEntity(
    product.id,
    product.name,
    product.code,
    product.price,
    product.description,
    product.brand,
    product.maker,
    product.origin,
    product.category,
    product.temperatureZone,
    product.weight,
    product.productSize.widthInMillimeters,
    product.productSize.heightInMillimeters,
    product.productSize.lengthInMillimeters,
)