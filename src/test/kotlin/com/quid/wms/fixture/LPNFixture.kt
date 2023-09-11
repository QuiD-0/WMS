package com.quid.wms.fixture

import com.quid.wms.inbound.domain.LPN
import com.quid.wms.inbound.gateway.repository.LPNRepository
import com.quid.wms.inbound.gateway.web.request.RegisterLPNRequest
import java.time.LocalDateTime

class LPNFixture {
    fun registerRequest() = RegisterLPNRequest(
        inboundItemId = 1,
        lpnBarcode = "LPN-1",
        expirationAt = LocalDateTime.now().plusDays(1)
    )

    fun repository() = LPNRepositoryFixture()
}

class LPNRepositoryFixture: LPNRepository{
    private val lpns = mutableMapOf<Long, LPN>()

    override fun save(lpn: LPN): LPN {
        lpns[lpn.id!!] = lpn
        return lpn
    }

}