package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.Item
import org.springframework.stereotype.Repository

interface ItemRepository {

    fun findById(id: Long): Item
    fun save(item: Item): Item

    @Repository
    class ItemRepositoryImpl : ItemRepository {
        override fun findById(id: Long): Item {
            TODO()
        }

        override fun save(item: Item): Item {
            TODO("Not yet implemented")
        }
    }

}