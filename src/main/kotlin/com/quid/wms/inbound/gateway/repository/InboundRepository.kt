package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.gateway.repository.jpa.InboundJpaRepository
import com.quid.wms.inbound.gateway.repository.jpa.inboundEntity
import org.springframework.stereotype.Repository

interface InboundRepository {
    fun findAll(): List<Inbound>
    fun findById(id: Long): Inbound
    fun save(inbound: Inbound): Inbound

    @Repository
    class InboundRepositoryImpl(
        private val jpaRepository: InboundJpaRepository
    ) : InboundRepository {
        override fun findAll(): List<Inbound> {
            return jpaRepository.findAll().map { it.toInbound() }
        }

        override fun findById(id: Long): Inbound {
            return jpaRepository.findById(id).orElseThrow().toInbound()
        }

        override fun save(inbound: Inbound): Inbound {
            return jpaRepository.save(inboundEntity(inbound)).toInbound()
        }

    }
}