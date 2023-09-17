package com.quid.wms.outbound.domain

data class OrderCustomer(
    val name: String,
    val address: String,
    val email: String,
    val phone: String,
    val zipCode: String,
) {
}