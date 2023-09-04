package com.quid.wms.inbound.gateway.web.request

data class RejectRequest(
    val rejectMessage: String
) {
    init {
        require(rejectMessage.isNotBlank()) { "rejectMessage is necessary" }
    }
}