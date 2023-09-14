package com.quid.wms.location.gateway.web

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.web.request.AssignLocationLpnRequest
import com.quid.wms.location.gateway.web.request.RegisterLocationRequest
import com.quid.wms.location.usecase.AssignLPN
import com.quid.wms.location.usecase.RegisterLocation
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/locations")
class LocationApiController(
    private val registLocation: RegisterLocation,
    private val assignLPN: AssignLPN
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
}