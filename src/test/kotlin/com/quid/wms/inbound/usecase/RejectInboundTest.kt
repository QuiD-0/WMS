package com.quid.wms.inbound.usecase

import com.quid.wms.fixture.InboundFixture
import com.quid.wms.inbound.domain.InboundStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RejectInboundTest{

    @Test
    fun rejectInbound(){
        val inboundId = 1L
        val inboundRepository = InboundFixture().repository()
        inboundRepository.save(InboundFixture().inbound())
        val rejectInbound = RejectInbound.RejectInboundImpl(inboundRepository)

        rejectInbound.execute(inboundId)

        val inbound = inboundRepository.findById(inboundId)
        assertEquals(inbound.status, InboundStatus.REJECTED)
    }
}