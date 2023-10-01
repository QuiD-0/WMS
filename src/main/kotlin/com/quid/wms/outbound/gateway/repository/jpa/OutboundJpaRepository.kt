package com.quid.wms.outbound.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface OutboundJpaRepository: JpaRepository<OutboundEntity, Long> {
}