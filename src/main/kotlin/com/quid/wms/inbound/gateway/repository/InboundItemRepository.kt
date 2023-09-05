package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.InboundItem
import com.quid.wms.inbound.gateway.repository.jpa.InboundItemEntity
import com.quid.wms.inbound.gateway.repository.jpa.InboundItemJpaRepository
import com.quid.wms.inbound.gateway.repository.jpa.inboundItemEntity
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
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
        override fun findById(id: Long): InboundItem =
            jpaRepository.findByIdOrNull(id)
                ?.toInboundItem()
                ?: throw EntityNotFoundException("존재하지 않는 입고상품입니다.")

        override fun save(inboundItem: InboundItem): InboundItem =
            jpaRepository.save(inboundItemEntity(inboundItem)).toInboundItem()

        override fun findAll(): List<InboundItem> =
            jpaRepository.findAll().map { it.toInboundItem() }

        override fun saveAll(item: List<InboundItem>): List<InboundItem> =
            jpaRepository.saveAll(item.map { inboundItemEntity(it) }).map { it.toInboundItem() }
    }

}