package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.InboundItem
import com.quid.wms.inbound.gateway.repository.jpa.InboundItemJpaRepository
import com.quid.wms.inbound.gateway.repository.jpa.inboundItemEntity
import org.springframework.stereotype.Repository

interface InboundItemRepository {

    fun findById(id: Long): InboundItem
    fun save(inboundItem: InboundItem): InboundItem
    fun findAll(): List<InboundItem>
    fun saveAll(item: List<InboundItem>): List<InboundItem>

    @Repository
    class InboundItemRepositoryImpl(
        private val jpaRepository: InboundItemJpaRepository
    ) : InboundItemRepository {
        override fun findById(id: Long): InboundItem {
            return jpaRepository.findById(id).orElseThrow().toInboundItem()
        }

        override fun save(inboundItem: InboundItem): InboundItem {
            return jpaRepository.save(inboundItemEntity(inboundItem)).toInboundItem()
        }

        override fun findAll(): List<InboundItem> {
            return jpaRepository.findAll().map { it.toInboundItem() }
        }

        override fun saveAll(item: List<InboundItem>): List<InboundItem> {
            return jpaRepository.saveAll(item.map { inboundItemEntity(it) }).map { it.toInboundItem() }
        }
    }

}