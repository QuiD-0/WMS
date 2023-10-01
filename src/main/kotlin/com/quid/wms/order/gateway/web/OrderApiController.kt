package com.quid.wms.order.gateway.web

import com.quid.wms.order.domain.Order
import com.quid.wms.order.usecase.FindOrder
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/order")
class OrderApiController(
    private val findOrder: FindOrder,
) {

    @GetMapping
    @ResponseStatus(OK)
    fun getOrders(): List<Order> = findOrder.all()

    @GetMapping("/{name}")
    @ResponseStatus(OK)
    fun getOrdersByName(@PathVariable name: String): List<Order> = findOrder.byName(name)

}