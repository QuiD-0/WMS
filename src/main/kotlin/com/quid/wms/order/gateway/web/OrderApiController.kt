package com.quid.wms.order.gateway.web

import com.quid.wms.order.domain.Order
import com.quid.wms.order.gateway.web.reqeust.CreateOrderRequest
import com.quid.wms.order.usecase.CreateOrder
import com.quid.wms.order.usecase.FindOrder
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/order")
class OrderApiController(
    private val createOrder: CreateOrder,
    private val findOrder: FindOrder,
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun createOrder(@RequestBody request: CreateOrderRequest): Long = createOrder.execute(request)


    @GetMapping
    @ResponseStatus(OK)
    fun getOrders(): List<Order> = findOrder.all()

}