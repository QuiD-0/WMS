package com.quid.wms.inbound.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InboundJpaRepository: JpaRepository<InboundEntity, Long> {
    @Query("select i from InboundEntity i join fetch i.inboundItems join fetch InboundItemEntity where i.id = :id")
    fun findByIdOrNull(id: Long): InboundEntity?

    @Query("select i from InboundEntity i join fetch i.inboundItems")
    override fun findAll(): List<InboundEntity>
}