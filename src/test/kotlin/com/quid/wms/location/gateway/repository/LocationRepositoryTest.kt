package com.quid.wms.location.gateway.repository

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Disabled
@SpringBootTest
class LocationRepositoryTest{

    @Autowired
    private lateinit var locationRepository: LocationRepository

    @Test
    @DisplayName("위치 조회 테스트")
    @Transactional(readOnly = true)
    fun test(){
        locationRepository.findLocationList(listOf(3L))
            .also { println(it) }
    }
}