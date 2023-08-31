package com.quid.wms.inbound.usecase

import com.quid.wms.fixture.InboundFixture
import com.quid.wms.fixture.InboundItemFixture
import com.quid.wms.inbound.usecase.RegisterInbound.RegisterInboundUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RegisterInboundTest {

    private val itemRepository = InboundItemFixture().repository()
    private val inboundRepository = InboundFixture().repository()
    private val registerInbound = RegisterInboundUseCase(inboundRepository,itemRepository)

    @Test
    @DisplayName("입고 등록")
    fun registerInbound() {
        val request = InboundFixture().registRequest()
        val item = InboundItemFixture().item()
        itemRepository.save(item)

        registerInbound.register(request)

        assert(inboundRepository.findAll().size == 1)
    }

}

