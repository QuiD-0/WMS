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
    fun updateAmount(lpn: LPN, amount: Int): Location {
        val locationLpn = findLPN(lpn)
        val updated = locationLpn.copy(quantity = amount.toLong())
        return copy(locationLPNList = locationLPNList.minus(locationLpn).plus(updated))
    }

    private fun addLocationLPN(lpn: LPN) =
        copy(locationLPNList = locationLPNList.plus(LocationLPN(lpn = lpn)))

    private fun increaseQuantity(locationLpn: LocationLPN): Location =
        copy(locationLPNList = locationLPNList.minus(locationLpn).plus(locationLpn.increaseQuantity()))

    private fun findLPN(lpn: LPN): LocationLPN = locationLPNList.first { it.lpn.isEqual(lpn) }

    private fun isExistLPN(lpn: LPN): Boolean = locationLPNList.any { it.lpn.isEqual(lpn) }
}