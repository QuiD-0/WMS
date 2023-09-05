package com.quid.wms.inbound.usecase

import com.quid.wms.fixture.InboundItemFixture
import com.quid.wms.inbound.gateway.web.request.RegisterLPNRequest
import com.quid.wms.inbound.usecase.RegisterLPN.RegisterLPNImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class RegisterLPNTest {

    private val repository = InboundItemFixture().repository()
    private val registerLPN: RegisterLPN = RegisterLPNImpl(repository)

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

        registerLPN.execute(request)

        assert(repository.findById(1).lpnList.size == 1)
    }
}