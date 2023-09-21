package com.quid.wms.inbound.gateway.web

import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import com.quid.wms.inbound.gateway.web.request.RejectRequest
import com.quid.wms.inbound.usecase.ConfirmInbound
import com.quid.wms.inbound.usecase.FindInbound
import com.quid.wms.inbound.usecase.RegisterInbound
import com.quid.wms.inbound.usecase.RejectInbound
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/inbounds")
class InboundApiController(
    private val registerInbound: RegisterInbound,
    private val confirmInbound: ConfirmInbound,
    private val rejectInbound: RejectInbound,
    private val findInbound: FindInbound,
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun request(@RequestBody request: RegistInboundRequest): Long =
        registerInbound.register(request)

    @PatchMapping("/{id}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun confirm(@PathVariable id: Long) {
        confirmInbound.execute(id)
    }

    @PatchMapping("/{id}/reject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun reject(@PathVariable id: Long, @RequestBody request: RejectRequest) {
        rejectInbound.execute(id, request.rejectMessage)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getInboundById(@PathVariable id: Long) = findInbound.byId(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getInboundList() = findInbound.all()

}