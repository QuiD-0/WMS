package com.quid.wms.fixture

import com.quid.wms.inbound.domain.LPN
import com.quid.wms.inbound.gateway.web.request.RegisterLPNRequest
import java.time.LocalDateTime

class LPNFixture {
    fun registerRequest() = RegisterLPNRequest(
        inboundItemId = 1,
        lpnBarcode = "LPN-1",
        expirationAt = LocalDateTime.now().plusDays(1)
    )

    fun lpn(): LPN = LPN(
        id = 1,
        lpnBarcode = "LPN-1",
        expirationAt = LocalDateTime.now().plusDays(1)
    )
}