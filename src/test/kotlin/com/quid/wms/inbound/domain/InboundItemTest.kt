package com.quid.wms.inbound.domain

import com.quid.wms.fixture.InboundItemFixture
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class InboundItemTest{

    @Test
    fun addLpn(){
        val inboundItem = InboundItemFixture().inboundItem()
        val lpn = LPN(1, "1234567890", LocalDateTime.now())

        val registerLPN = inboundItem.registerLPN(lpn)

        assertEquals(1, registerLPN.lpnList.size)
    }
}