package com.quid.wms.location.gateway.web

import com.quid.wms.location.domain.Location
import com.quid.wms.location.gateway.web.request.RegisterLocationRequest
import com.quid.wms.location.usecase.RegisterLocation
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/locations")
class LocationApiController(
    private val registLocation: RegisterLocation
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun request(@RequestBody request: RegisterLocationRequest): Location =
        request.toLocation()
            .let { registLocation.execute(it) }
}