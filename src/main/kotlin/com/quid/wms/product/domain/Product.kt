package com.quid.wms.product.domain

data class Product(
    val id: Long? = null,
    val name: String,
    val code: String,
    val price: Int,
    val description: String,
    val brand: String,
    val maker: String,
    val origin: String,
    val category: Category,
    val temperatureZone: TemperatureZone,
    val weight: Long,
    val productSize: ProductSize,
) {
    init {
        require(name.isNotBlank()) { "상품명은 필수입니다." }
        require(code.isNotBlank()) { "상품 코드는 필수입니다." }
        require(description.isNotBlank()) { "상품 설명은 필수입니다." }
        require(brand.isNotBlank()) { "상품 브랜드는 필수입니다." }
        require(maker.isNotBlank()) { "상품 제조사는 필수입니다." }
        require(origin.isNotBlank()) { "상품 원산지는 필수입니다." }
        require(weight > 0) { "상품 무게는 0보다 커야합니다." }
    }
}