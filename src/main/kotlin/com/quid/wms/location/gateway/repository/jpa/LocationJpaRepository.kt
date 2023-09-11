package com.quid.wms.location.gateway.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface LocationJpaRepository : JpaRepository<LocationEntity, Long>{
}