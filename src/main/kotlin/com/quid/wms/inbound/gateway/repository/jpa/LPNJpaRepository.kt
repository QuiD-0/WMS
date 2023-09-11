package com.quid.wms.inbound.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface LPNJpaRepository: JpaRepository<LPNEntity, Long> {
}