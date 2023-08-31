package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.InboundItem
import org.springframework.stereotype.Repository

interface InboundItemRepository {

    fun findById(id: Long): InboundItem
    fun save(inboundItem: InboundItem): InboundItem

    @Repository
    class InboundItemRepositoryImpl : InboundItemRepository {
        override fun findById(id: Long): InboundItem {
            TODO()
        }

        override fun save(inboundItem: InboundItem): InboundItem {
            TODO("Not yet implemented")
        }
    }

}