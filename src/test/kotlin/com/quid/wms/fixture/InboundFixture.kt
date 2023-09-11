package com.quid.wms.fixture

import com.quid.wms.inbound.domain.Inbound
import com.quid.wms.inbound.domain.InboundStatus
import com.quid.wms.inbound.gateway.repository.InboundRepository
import com.quid.wms.inbound.gateway.web.request.RegistInboundRequest
import com.quid.wms.inbound.gateway.web.request.RegistItemRequest
import java.time.LocalDateTime

class InboundFixture {

    fun registRequest() = RegistInboundRequest(
        "title",
        "description",
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(1),
        listOf(
            RegistItemRequest(1, 10, 10000),
            RegistItemRequest(3, 15, 20000)
        )
    )

    fun inbound(item : Long? = 1) = Inbound(
        1,
        "title",
        "description",
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(1),
        listOf(
            InboundItemFixture().inboundItem(item)
        ),
        InboundStatus.REQUESTED
    )

    fun completedInbound(item : Long? = 1) = Inbound(
        1,
        "title",
        "description",
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(1),
        listOf(
            InboundItemFixture().inboundItem(item)
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