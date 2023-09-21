package com.quid.wms.order.domain

data class OrderCustomer(
    val name: String,
    val address: String,
    val email: String,
    val phone: String,
    val zipCode: String,
) {
}