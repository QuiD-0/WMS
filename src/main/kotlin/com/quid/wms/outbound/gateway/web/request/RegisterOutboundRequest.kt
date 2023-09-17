package com.quid.wms.outbound.gateway.web.request

data class RegisterOutboundRequest(
    val orderId: Long,
    val isPriorityDelivery: Boolean,
    val desiredDeliveryDate: String,
) {
    init {
        require(orderId > 0) { "orderId must be greater than 0" }
    }
}