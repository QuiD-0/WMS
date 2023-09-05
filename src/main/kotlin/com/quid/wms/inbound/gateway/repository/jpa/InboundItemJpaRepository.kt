package com.quid.wms.inbound.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InboundItemJpaRepository: JpaRepository<InboundItemEntity, Long> {

    @Query("select i from InboundItemEntity i join fetch i.product join fetch i.lpnList where i.id = :id")
    fun findByIdOrNull(id: Long): InboundItemEntity?

    @Query("select i from InboundItemEntity i join fetch i.product join fetch i.lpnList")
    override fun findAll(): List<InboundItemEntity>
}