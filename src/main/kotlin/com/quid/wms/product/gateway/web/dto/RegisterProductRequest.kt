package com.quid.wms.product.gateway.web.dto

import com.quid.wms.product.domain.Category
import com.quid.wms.product.domain.Product
import com.quid.wms.product.domain.ProductSize
import com.quid.wms.product.domain.TemperatureZone

data class RegisterProductRequest(
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
    init {
        require(name.isNotBlank()) { "상품명은 필수입니다." }
        require(code.isNotBlank()) { "상품 코드는 필수입니다." }
        require(description.isNotBlank()) { "상품 설명은 필수입니다." }
        require(brand.isNotBlank()) { "상품 브랜드는 필수입니다." }
        require(maker.isNotBlank()) { "상품 제조사는 필수입니다." }
        require(origin.isNotBlank()) { "상품 원산지는 필수입니다." }
        require(weight > 0) { "상품 무게는 0보다 커야합니다." }
        require(widthInMillimeters > 0) { "상품 가로 길이는 0보다 커야합니다." }
        require(heightInMillimeters > 0) { "상품 세로 길이는 0보다 커야합니다." }
        require(lengthInMillimeters > 0) { "상품 세로 길이는 0보다 커야합니다." }
    }

    fun toDomain() = Product(
        1L, name, code, description, brand, maker, origin, category, temperatureZone, weight, ProductSize(
            widthInMillimeters, heightInMillimeters, lengthInMillimeters
        )
    )
}