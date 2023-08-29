package com.quid.wms.product.gateway.repository

import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.Product
import com.quid.wms.product.domain.ProductSize
import com.quid.wms.product.domain.TemperatureZone
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import org.hibernate.annotations.Comment

@Entity
@Table(name = "product")
@Comment("상품")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val code: String,
    val description: String,
    val brand: String,
    val maker: String,
    val origin: String,
    val category: Category,
    @Enumerated(STRING)
    val temperatureZone: TemperatureZone,
    val weight: Long,
    val widthInMillimeters: Long,
    val heightInMillimeters: Long,
    val lengthInMillimeters: Long,
) {
    fun toDomain() = Product(
        id,
        name,
        code,
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

fun fromDomain(product: Product) = ProductEntity(
    product.id,
    product.name,
    product.code,
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