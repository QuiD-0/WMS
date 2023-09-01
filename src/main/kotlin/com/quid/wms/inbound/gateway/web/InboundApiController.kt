package com.quid.wms.inbound.gateway.web

import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import com.quid.wms.inbound.usecase.RegisterInbound
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/inbounds")
class InboundApiController(
    private val registerInbound: RegisterInbound
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun request(@RequestBody request: RegistInboundRequest) {
        registerInbound.register(request)
    }
}