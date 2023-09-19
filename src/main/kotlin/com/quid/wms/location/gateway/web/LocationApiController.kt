package com.quid.wms.location.gateway.web

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.web.request.AssignLocationLpnRequest
import com.quid.wms.location.gateway.web.request.RegisterLPNRequest
import com.quid.wms.location.gateway.web.request.RegisterLocationRequest
import com.quid.wms.location.gateway.web.request.UpdateLocationLPNAmountRequest
import com.quid.wms.location.usecase.AssignLPN
import com.quid.wms.location.usecase.RegisterLPN
import com.quid.wms.location.usecase.RegisterLocation
import com.quid.wms.location.usecase.UpdateLocationLPNAmount
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/locations")
class LocationApiController(
    private val registLocation: RegisterLocation,
    private val assignLPN: AssignLPN,
    private val registerLPN: RegisterLPN,
    private val updateLocationLPNAmount: UpdateLocationLPNAmount
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun registerLocation(@RequestBody request: RegisterLocationRequest): Location =
        request.toLocation()
            .let { registLocation.execute(it) }

    @PostMapping("/register-lpn")
    @ResponseStatus(CREATED)
    fun assignLPN(@RequestBody request: AssignLocationLpnRequest): Location =
        assignLPN.assign(request.lpnBarcode, request.locationBarcode)

    @PostMapping("/update-amount")
    @ResponseStatus(OK)
    fun updateAmount(@RequestBody request: UpdateLocationLPNAmountRequest): Location =
        updateLocationLPNAmount.modify(request.locationBarcode, request.lpnBarcode, request.amount)

    @PostMapping("/lpns")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerLPN(@RequestBody request: RegisterLPNRequest) =
        registerLPN.execute(request)

}