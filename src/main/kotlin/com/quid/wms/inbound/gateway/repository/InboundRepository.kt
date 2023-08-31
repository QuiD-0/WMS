package com.quid.wms.inbound.gateway.repository

import com.quid.wms.inbound.domain.Inbound
import org.springframework.stereotype.Repository

interface InboundRepository {
    fun findAll(): List<Inbound>
    fun findById(id: Long): Inbound
    fun save(inbound: Inbound): Inbound

    @Repository
    class InboundRepositoryImpl : InboundRepository {
        override fun findAll(): List<Inbound> {
            TODO("Not yet implemented")
        }

        override fun findById(id: Long): Inbound {
            TODO("Not yet implemented")
        }

        override fun save(inbound: Inbound): Inbound {
            TODO("Not yet implemented")
        }

    }
}