package com.quid.wms.fixture

import com.quid.wms.inbound.domain.Item
import com.quid.wms.inbound.gateway.repository.ItemRepository

class ItemFixture {

    fun item() = Item(
        id = 1,
        productId = 1,
        quantity = 1,
        unitPrice = 1000
    )

    fun repository() = ItemRepositoryFixture()
}

class ItemRepositoryFixture: ItemRepository {
    private val items = mutableMapOf<Long, Item>()

    override fun findById(id: Long): Item = items[id]!!
    override fun save(item: Item): Item {
        val id = item.id?:1
        items[id] = item
        return item.copy(id = id)
    }
}