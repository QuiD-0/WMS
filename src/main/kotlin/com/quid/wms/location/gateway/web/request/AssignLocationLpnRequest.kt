package com.quid.wms.location.gateway.web.request

data class AssignLocationLpnRequest(
    val locationBarcode: String,
    val lpnBarcode: String,
) {
}