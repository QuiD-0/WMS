package com.quid.wms.inbound.gateway.web

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import com.quid.wms.inbound.usecase.ConfirmInbound
import com.quid.wms.inbound.usecase.RegisterInbound
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/inbounds")
class InboundApiController(
    private val registerInbound: RegisterInbound,
    private val confirmInbound: ConfirmInbound
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun request(@RequestBody request: RegistInboundRequest): Inbound =
        registerInbound.register(request)

    @PatchMapping("/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun confirm(@PathVariable id: Long) {
        confirmInbound.execute(id)
    }
}