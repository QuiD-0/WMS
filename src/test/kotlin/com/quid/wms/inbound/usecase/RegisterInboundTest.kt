package com.quid.wms.inbound.usecase

import com.quid.wms.fixture.InboundFixture
import com.quid.wms.fixture.ItemFixture
import com.quid.wms.inbound.gateway.web.request.RegisterInboundRequest
import com.quid.wms.inbound.usecase.RegisterInbound.RegisterInboundUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class RegisterInboundTest {

    private val itemRepository = ItemFixture().repository()
    private val inboundRepository = InboundFixture().repository()
    private val registerInbound = RegisterInboundUseCase(inboundRepository,itemRepository)

    @Test
    @DisplayName("입고 등록")
    fun registerInbound() {
        val request = InboundFixture().registRequest()
        val item = ItemFixture().item()
        itemRepository.save(item)

        registerInbound.register(request)

        assert(inboundRepository.findAll().size == 1)
    }

}

