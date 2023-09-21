package com.quid.wms.order.domain

import java.util.*

class DeliveryInfo(
    val address: String,
    val zipCode: String,
    val memo: String,
    val deliveryNumber: UUID = initDeliveryNumber(),
) {
}

fun initDeliveryNumber(): UUID = UUID.randomUUID()
