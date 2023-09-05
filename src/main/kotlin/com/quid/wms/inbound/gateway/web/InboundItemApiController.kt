package com.quid.wms.inbound.gateway.web

import com.quid.wms.inbound.gateway.web.request.RegisterLPNRequest
import com.quid.wms.inbound.usecase.RegisterLPN
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/inbounds/inbound-items")
class InboundItemApiController(
    private val registerLPN: RegisterLPN,
) {
    @PostMapping("/lpns")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerLPN(@RequestBody request: RegisterLPNRequest) =
        registerLPN.execute(request)

}