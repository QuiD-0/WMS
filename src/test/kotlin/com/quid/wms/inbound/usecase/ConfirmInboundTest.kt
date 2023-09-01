package com.quid.wms.inbound.usecase

import com.quid.wms.fixture.InboundFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ConfirmInboundTest{

    private val inboundRepository = InboundFixture().repository()
    private val confirmInbound = ConfirmInbound.ConfirmInboundUseCase(inboundRepository)

    @Test
    @DisplayName("입고 승인")
    fun confirm(){
        inboundRepository.save(InboundFixture().inbound())

        assertDoesNotThrow { confirmInbound.execute(1) }
    }

    @Test
    @DisplayName("입고 실패")
    fun confirmFail(){
        inboundRepository.save(InboundFixture().completedInbound())

        assertThrows<IllegalStateException> { confirmInbound.execute(1) }
    }
}