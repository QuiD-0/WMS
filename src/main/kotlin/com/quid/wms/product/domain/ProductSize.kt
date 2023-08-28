package com.quid.wms.product.domain

data class ProductSize(
    val widthInMillimeters: Long,
    val heightInMillimeters: Long,
    val lengthInMillimeters: Long,
) {
    init {
        require(widthInMillimeters > 0) { "상품 가로 길이는 0보다 커야합니다." }
        require(heightInMillimeters > 0) { "상품 세로 길이는 0보다 커야합니다." }
        require(lengthInMillimeters > 0) { "상품 세로 길이는 0보다 커야합니다." }
    }
}