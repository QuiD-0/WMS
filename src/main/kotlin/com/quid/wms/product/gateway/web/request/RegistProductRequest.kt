package com.quid.wms.product.gateway.web.request

import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.Product
import com.quid.wms.product.domain.ProductSize
import com.quid.wms.product.domain.TemperatureZone

data class RegistProductRequest(
    val name: String,
    val code: String,
    val description: String,
    val brand: String,
    val maker: String,
    val origin: String,
    val category: Category,
    val temperatureZone: TemperatureZone,
    val weight: Long,
    val widthInMillimeters: Long,
    val heightInMillimeters: Long,
    val lengthInMillimeters: Long,
) {
    fun toDomain() = Product(
        null, name, code, description, brand, maker, origin, category, temperatureZone, weight, ProductSize(
            widthInMillimeters, heightInMillimeters, lengthInMillimeters
        )
    )
}