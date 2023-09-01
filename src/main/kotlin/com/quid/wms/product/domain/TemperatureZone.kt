package com.quid.wms.product.domain

enum class TemperatureZone(
    val description: String
) {
    ROOM_TEMPERATURE("상온"),
    COLD_TEMPERATURE("냉장"),
    FROZEN_TEMPERATURE("냉동"),
}