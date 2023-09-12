package com.quid.wms.fixture

import com.quid.wms.location.domain.Location
import com.quid.wms.location.domain.StorageType
import com.quid.wms.location.domain.UsagePurpose
import com.quid.wms.location.gateway.web.request.RegisterLocationRequest

class LocationFixture {
    fun registRequest() = RegisterLocationRequest(
        locationBarcode = "LOC-001",
        storageType = StorageType.TOTE,
        usagePurpose = UsagePurpose.MOVE
    )

    companion object {
        fun location(): Location =  Location(
            1L,
            "test-location-barcode",
            StorageType.TOTE,
            UsagePurpose.MOVE,
        )
    }
}