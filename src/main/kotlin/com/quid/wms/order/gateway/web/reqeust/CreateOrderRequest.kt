package com.quid.wms.order.gateway.web.reqeust

import com.quid.wms.order.domain.DeliveryInfo

data class CreateOrderRequest(
    val userId: Long,
    val productList: Map<Long, Int>,
    val deliveryInfo: DeliveryInfo,
) {
    val productQuantityList: List<ProductQuantity>
        get() {
            return productList.map { ProductQuantity(it.key, it.value) }
        }
}