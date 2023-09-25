package com.quid.wms.location.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LocationJpaRepository : JpaRepository<LocationEntity, Long>{
    fun findByLocationBarcode(locationBarcode: String): LocationEntity?

    @Query("select l.* from location l inner join lpn on l.location_id = lpn.location_id where lpn.product_id in :findProductIdList", nativeQuery = true)
    fun findLocationList(findProductIdList: List<Long>): List<LocationEntity>
}