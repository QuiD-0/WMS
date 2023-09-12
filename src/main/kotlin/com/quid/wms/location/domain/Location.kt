package com.quid.wms.location.domain

import com.quid.wms.inbound.domain.LPN

data class Location(
    val id: Long?,
    val locationBarcode: String,
    val storageType: StorageType,
    val usagePurpose: UsagePurpose,
    val locationLPNList: List<LocationLPN> = listOf(),
) {
    fun assignLPN(lpn: LPN): Location =
        if (isExistLPN(lpn)) {
            increaseQuantity(findLPN(lpn))
        } else {
            addLocationLPN(lpn)
        }

    private fun addLocationLPN(lpn: LPN) =
        copy(locationLPNList = locationLPNList + LocationLPN(location = this, lpn = lpn))

    private fun increaseQuantity(locationLpn: LocationLPN): Location =
        copy(locationLPNList = locationLPNList - locationLpn + locationLpn.increaseQuantity())

    private fun findLPN(lpn: LPN): LocationLPN = locationLPNList.first { it.lpn.isEqual(lpn) }

    private fun isExistLPN(lpn: LPN): Boolean = locationLPNList.any { it.lpn.isEqual(lpn) }
}