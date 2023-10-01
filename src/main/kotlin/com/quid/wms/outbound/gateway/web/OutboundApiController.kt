package com.quid.wms.outbound.gateway.web

import com.quid.wms.outbound.domain.Outbound
import com.quid.wms.outbound.gateway.web.request.RegisterOutboundRequest
import com.quid.wms.outbound.usecase.RegisterOutbound
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/outbound")
class OutboundApiController(
    private val registerOutbound: RegisterOutbound,
) {

    @PostMapping
    fun registerOutbound(@RequestBody request: RegisterOutboundRequest): Outbound = registerOutbound.registerOutbound(request)
}