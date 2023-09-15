package com.quid.wms.location.domain

import com.quid.wms.inbound.domain.LPN

data class Location(
    val id: Long?,
    val locationBarcode: String,
    val storageType: StorageType,
    val usagePurpose: UsagePurpose,
    val locationLPNList: List<LocationLPN> = listOf(),
) {
    fun assignLPN(lpn: LPN): Location {
        findLPN(lpn)
            ?.let { return increaseQuantity(it) }
            ?: return addLocationLPN(lpn)
    }

    fun updateAmount(lpn: LPN, amount: Long): Location {
        val locationLpn = findLPN(lpn)?: throw IllegalArgumentException("lpn not found")
        val updated = locationLpn.copy(quantity = amount)
        return copy(locationLPNList = locationLPNList.minus(locationLpn).plus(updated))
    }

    private fun addLocationLPN(lpn: LPN) =
        copy(locationLPNList = locationLPNList.plus(LocationLPN(lpn = lpn)))

    private fun increaseQuantity(locationLpn: LocationLPN): Location =
        copy(locationLPNList = locationLPNList.minus(locationLpn).plus(locationLpn.increaseQuantity()))

    private fun findLPN(lpn: LPN): LocationLPN? = locationLPNList.find { it.lpn.isEqual(lpn) }
}