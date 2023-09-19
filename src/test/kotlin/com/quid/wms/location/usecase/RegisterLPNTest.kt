package com.quid.wms.location.usecase

import com.quid.wms.fixture.InboundItemFixture
import com.quid.wms.fixture.LPNFixture
import com.quid.wms.location.gateway.web.request.RegisterLPNRequest
import com.quid.wms.location.usecase.RegisterLPN.RegisterLPNImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDateTime

class RegisterLPNTest {

    private val repository = InboundItemFixture().repository()
    private val lpnRepository = LPNFixture().repository()
    private val registerLPN: RegisterLPN = RegisterLPNImpl(repository,lpnRepository)

    @BeforeEach
    fun setUp() {
        repository.save(InboundItemFixture().inboundItem())
    }

    @Test
    fun registerLPN() {
        val request = RegisterLPNRequest(
            inboundItemId = 1,
            lpnBarcode = "LPN-1",
            expirationAt = LocalDateTime.now().plusDays(1)
        )

        assertDoesNotThrow { registerLPN.execute(request) }
    }
}