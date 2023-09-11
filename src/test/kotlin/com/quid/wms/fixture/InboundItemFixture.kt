package com.quid.wms.fixture

import com.quid.wms.inbound.domain.InboundItem
import com.quid.wms.inbound.gateway.repository.InboundItemRepository
import com.quid.wms.inbound.gateway.web.request.RegistItemRequest

class InboundItemFixture {

    fun registItemRequest() = RegistItemRequest(
        1,
        10,
        10000
    )

    fun repository() = InboundItemRepositoryFixture()
    fun inboundItem(id: Long? = 1): InboundItem = InboundItem(
        id,
        ProductFixture().product(),
        1,
        1000,
    )
}

class InboundItemRepositoryFixture: InboundItemRepository {
    private val items = mutableMapOf<Long, InboundItem>()

    override fun findById(id: Long): InboundItem = items[id]!!

    override fun save(inboundItem: InboundItem): InboundItem {
        val id = inboundItem.id?:1
        items[id] = inboundItem
        return inboundItem.copy(id = id)
    }
    override fun findAll(): List<InboundItem> = items.values.toList()
    override fun saveAll(item: List<InboundItem>): List<InboundItem> {
        item.forEach { save(it) }
        return item
    }
}