package com.quid.wms.fixture

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.domain.InboundStatus
import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import java.time.LocalDateTime

class InboundFixture {

    fun registRequest() = RegistInboundRequest(
        "title",
        "description",
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(1),
        listOf(
            InboundItemFixture().registItemRequest()
        )
    )

    fun inbound() = Inbound(
        1,
        "title",
        "description",
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(1),
        listOf(
            InboundItemFixture().inboundItem()
        ),
        InboundStatus.REQUESTED
    )

    fun completedInbound() = Inbound(
        1,
        "title",
        "description",
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(1),
        listOf(
            InboundItemFixture().inboundItem()
        ),
        InboundStatus.COMPLETED
    )

    fun repository() = InboundRepositoryFixture()
}

class InboundRepositoryFixture: InboundRepository {
    private val inbounds = mutableMapOf<Long, Inbound>()

    override fun findAll(): List<Inbound> {
        return inbounds.values.toList()
    }

    override fun findById(id: Long): Inbound {
        return inbounds[id]!!
    }

    override fun save(inbound: Inbound): Inbound {
        val id = inbound.id?:1
        inbounds[id] = inbound
        return inbound.copy(id = id)
    }

}