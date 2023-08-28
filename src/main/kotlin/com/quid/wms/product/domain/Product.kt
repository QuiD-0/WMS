package com.quid.wms.product.domain

data class Product(
    val id: Long,
    val name: String,
    val code: String,
    val description: String,
    val brand: String,
    val maker: String,
    val origin: String,
    val category: Category,
    val temperatureZone: TemperatureZone,
    val weight: Long,
    val size: ProductSize,
)