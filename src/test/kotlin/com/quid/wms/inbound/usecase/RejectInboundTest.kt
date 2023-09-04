package com.quid.wms.inbound.usecase

import com.quid.wms.fixture.InboundFixture
import com.quid.wms.inbound.domain.InboundStatus
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class RejectInboundTest{

    private val inboundRepository = InboundFixture().repository()
    private val rejectInbound = RejectInbound.RejectInboundImpl(inboundRepository)

    @Test
    @DisplayName("입고 거절")
    fun confirm(){
        inboundRepository.save(InboundFixture().inbound())

        rejectInbound.execute(1)

        assert(inboundRepository.findById(1).status == InboundStatus.REJECTED)
    }

    @Test
    @DisplayName("입고 거절 실패")
    fun confirmFail(){
        inboundRepository.save(InboundFixture().completedInbound())

        assertThrows<IllegalStateException> { rejectInbound.execute(1) }
    }
}