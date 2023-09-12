package com.quid.wms.location.domain

import com.quid.wms.inbound.domain.LPN

data class Location(
    val id: Long?,
    val locationBarcode: String,
    val storageType: StorageType,
    val usagePurpose: UsagePurpose,
    val locationLPN: List<LocationLPN> = listOf(),
) {
    fun assignLPN(lpn: LPN) : Any {
        return if(isExistLPN(lpn)) {
            increaseQuantity(findLPN(lpn))
        } else {
            copy(locationLPN = locationLPN + LocationLPN(location = this, lpn = lpn))
        }
    }

    private fun increaseQuantity(locationLpn: LocationLPN): Location {
        return copy(locationLPN = locationLPN - locationLpn + locationLpn.increaseQuantity())
    }

    private fun findLPN(lpn: LPN): LocationLPN {
        return locationLPN.first { it.lpn.isEqual(lpn) }
    }

    private fun isExistLPN(lpn: LPN): Boolean {
        return locationLPN.any { it.lpn.isEqual(lpn) }
    }
}