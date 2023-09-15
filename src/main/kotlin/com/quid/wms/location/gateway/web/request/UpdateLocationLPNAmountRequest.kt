package com.quid.wms.location.gateway.web.request

data class UpdateLocationLPNAmountRequest(
    val locationBarcode: String,
    val lpnBarcode: String,
    val amount: Long
)