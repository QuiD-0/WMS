package com.quid.wms.product.domain

enum class Category(
    val description: String
) {
    ELECTRONICS("전자 제품"),
    FOOD("식품"),
    CLOTHES("의류"),
    COSMETICS("화장품"),
    BOOKS("도서"),
}