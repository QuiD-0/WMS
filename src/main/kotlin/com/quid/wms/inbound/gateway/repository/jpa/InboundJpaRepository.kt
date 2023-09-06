package com.quid.wms.inbound.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface InboundJpaRepository: JpaRepository<InboundEntity, Long> {
}