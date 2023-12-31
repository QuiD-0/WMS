package com.quid.wms.inbound.usecase

import com.quid.wms.fixture.InboundFixture
import com.quid.wms.fixture.InboundItemFixture
import com.quid.wms.fixture.ProductFixture
import com.quid.wms.inbound.usecase.RegisterInbound.RegisterInboundUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RegisterInboundTest {

    private val productRepository = ProductFixture().repository()
    private val itemRepository = InboundItemFixture().repository()
    private val inboundRepository = InboundFixture().repository()
    private val registerInbound = RegisterInboundUseCase(productRepository,inboundRepository)

    @Test
    @DisplayName("입고 등록")
    fun registerInbound() {
        val request = InboundFixture().registRequest()
        productRepository.save(ProductFixture().product())

        registerInbound.register(request)

        assert(inboundRepository.findAll().size == 1)
    }

    @Test
    @DisplayName("아이템 등록")
    fun registerInboundItem() {
        val item = InboundItemFixture().inboundItem()
        productRepository.save(ProductFixture().product())

        itemRepository.save(item)

        assert(itemRepository.findAll().size == 1)
    }

}

