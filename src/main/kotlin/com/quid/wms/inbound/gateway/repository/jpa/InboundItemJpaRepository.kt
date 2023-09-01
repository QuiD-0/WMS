package com.quid.wms.inbound.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface InboundItemJpaRepository: JpaRepository<InboundItemEntity, Long> {
}