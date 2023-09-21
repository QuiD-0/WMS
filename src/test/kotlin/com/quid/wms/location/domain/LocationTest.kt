package com.quid.wms.location.domain

import com.quid.wms.fixture.LPNFixture
import com.quid.wms.fixture.LocationFixture
import org.junit.jupiter.api.Test

class LocationTest{

    @Test
    fun addLpnTest() {
        val location = LocationFixture.location()
        val lpn = LPNFixture().lpn()

        val assigned = location.assignLPN(lpn)

        assert(assigned.lpnList.size == 1)
    }

    @Test
    fun addLpnTestWhenAlreadyExist() {
        val location = LocationFixture.location()
        val lpn = LPNFixture().lpn()

        val assigned1 = location.assignLPN(lpn)
        val assigned2 = assigned1.assignLPN(lpn)

        assert(assigned2.lpnList.first().quantity == 2L )
    }


}