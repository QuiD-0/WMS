package com.quid.wms.fixture

import com.quid.wms.location.domain.LPN
import com.quid.wms.location.gateway.repository.LPNRepository
import com.quid.wms.location.gateway.web.request.RegisterLPNRequest
import java.time.LocalDateTime

class LPNFixture {
    fun registerRequest() = RegisterLPNRequest(
        inboundItemId = 1,
        lpnBarcode = "LPN-1",
        expirationAt = LocalDateTime.now().plusDays(1)
    )

    fun repository(): LPNRepository = object : LPNRepository {
        private val lpns = mutableListOf<LPN>()
        override fun save(lpn: LPN): LPN = lpn.also { lpns.add(it) }
        override fun findByBarcode(lpnBarcode: String): LPN {
            return lpns.find { it.lpnBarcode == lpnBarcode } ?: throw Exception("LPN not found")
        }
    }

    fun lpn(): LPN = LPN(
        id = 1,
        lpnBarcode = "LPN-1",
        productId = 1,
        inboundItemId = 1,
        expirationAt = LocalDateTime.now().plusDays(1)
    )
}