package com.quid.wms.location.gateway.web.request

import com.quid.wms.location.domain.Location
import com.quid.wms.location.domain.StorageType
import com.quid.wms.location.domain.UsagePurpose

data class RegisterLocationRequest(
    val locationBarcode: String,
    val storageType: StorageType,
    val usagePurpose: UsagePurpose
) {
    fun toLocation() = Location(null,locationBarcode,storageType,usagePurpose)
}