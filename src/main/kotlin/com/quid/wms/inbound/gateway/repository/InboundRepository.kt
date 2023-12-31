package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.gateway.repository.jpa.InboundJpaRepository
import com.quid.wms.inbound.gateway.repository.jpa.inboundEntity
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface InboundRepository {
    fun findAll(): List<Inbound>
    fun findById(id: Long): Inbound
    fun save(inbound: Inbound): Inbound

    @Repository
    class InboundRepositoryImpl(
        private val jpaRepository: InboundJpaRepository,
    ) : InboundRepository {
        override fun findAll(): List<Inbound> = jpaRepository.findAll().map { it.toInbound() }

        override fun findById(id: Long): Inbound = jpaRepository.findByIdOrNull(id)?.toInbound()
            ?: throw EntityNotFoundException("존재하지 않는 입고지시입니다.")

        override fun save(inbound: Inbound): Inbound =
            jpaRepository.save(inboundEntity(inbound)).toInbound()
    }
}