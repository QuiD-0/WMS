package com.quid.wms.fixture

import com.quid.wms.inbound.domain.InboundItem
import com.quid.wms.inbound.gateway.repository.InboundItemRepository

class InboundItemFixture {

    fun item() = InboundItem(
        id = 1,
        productId = 1,
        quantity = 1,
        unitPrice = 1000
    )

    fun repository() = InboundItemRepositoryFixture()
}

class InboundItemRepositoryFixture: InboundItemRepository {
    private val items = mutableMapOf<Long, InboundItem>()

    override fun findById(id: Long): InboundItem = items[id]!!
    override fun save(inboundItem: InboundItem): InboundItem {
        val id = inboundItem.id?:1
        items[id] = inboundItem
        return inboundItem.copy(id = id)
    }
}