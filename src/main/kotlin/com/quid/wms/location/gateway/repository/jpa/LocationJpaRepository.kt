package com.quid.wms.location.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LocationJpaRepository : JpaRepository<LocationEntity, Long>{
    fun findByLocationBarcode(locationBarcode: String): LocationEntity?

    @Query("SELECT l FROM LocationEntity l inner join fetch LPNEntity lpn WHERE lpn.productId IN :findProductIdList")
    fun findLocationList(findProductIdList: List<Long>): List<LocationEntity>
}