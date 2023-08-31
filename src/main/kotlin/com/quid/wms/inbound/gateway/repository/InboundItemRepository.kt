package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.InboundItem
import org.springframework.stereotype.Repository

interface InboundItemRepository {

    fun findById(id: Long): InboundItem
    fun save(inboundItem: InboundItem): InboundItem
    fun findAll(): List<InboundItem>
    fun saveAll(item: List<InboundItem>): List<InboundItem>

    @Repository
    class InboundItemRepositoryImpl : InboundItemRepository {
        override fun findById(id: Long): InboundItem {
            TODO()
        }

        override fun save(inboundItem: InboundItem): InboundItem {
            TODO("Not yet implemented")
        }

        override fun findAll(): List<InboundItem> {
            TODO("Not yet implemented")
        }

        override fun saveAll(item: List<InboundItem>): List<InboundItem> {
            TODO("Not yet implemented")
        }
    }

}