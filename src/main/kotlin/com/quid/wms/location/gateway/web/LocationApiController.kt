package com.quid.wms.location.gateway.web

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.web.request.AssignLocationLpnRequest
import com.quid.wms.location.gateway.web.request.RegisterLPNRequest
import com.quid.wms.location.gateway.web.request.RegisterLocationRequest
import com.quid.wms.location.gateway.web.request.UpdateLocationLPNAmountRequest
import com.quid.wms.location.usecase.*
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/locations")
class LocationApiController(
    private val registLocation: RegisterLocation,
    private val assignLPN: AssignLPN,
    private val registerLPN: RegisterLPN,
    private val updateLocationLPNAmount: UpdateLocationLPNAmount,
    private val findLocation: FindLocation
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
    @ResponseStatus(CREATED)
    fun registerLPN(@RequestBody request: RegisterLPNRequest) =
        registerLPN.execute(request)

    @GetMapping("/{barcode}")
    @ResponseStatus(OK)
    fun findByBarcode(@PathVariable barcode: String): Location = findLocation.byBarcode(barcode)

    @GetMapping
    @ResponseStatus(OK)
    fun findAll(): List<Location> = findLocation.findAll()
}