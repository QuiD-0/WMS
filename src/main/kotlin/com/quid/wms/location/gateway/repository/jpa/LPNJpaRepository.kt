package com.quid.wms.location.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface LPNJpaRepository: JpaRepository<LPNEntity, Long> {
    fun findByLpnBarcode(lpnBarcode: String): LPNEntity?
}