package com.quid.wms.outbound.gateway.web

import com.quid.wms.outbound.domain.Order
import com.quid.wms.outbound.domain.Outbound
import com.quid.wms.outbound.gateway.web.request.RegisterOutboundRequest
import com.quid.wms.outbound.usecase.FindOrder
import com.quid.wms.outbound.usecase.RegisterOutbound
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/outbound")
class OutboundApiController(
    private val registerOutbound: RegisterOutbound,
    private val findOrder: FindOrder,
) {

    @PostMapping
    fun registerOutbound(@RequestBody request: RegisterOutboundRequest): Outbound = registerOutbound.registerOutbound(request)

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    fun getOrders(): List<Order> = findOrder.all()

    @GetMapping("/order/{name}")
    @ResponseStatus(HttpStatus.OK)
    fun getOrdersByName(@PathVariable name: String): List<Order> = findOrder.byName(name)
}