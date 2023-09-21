package com.quid.wms.location.domain

data class Location(
    val id: Long?,
    val locationBarcode: String,
    val storageType: StorageType,
    val usagePurpose: UsagePurpose,
    val lpnList: List<LPN> = listOf(),
) {
    fun updateAmount(lpn: LPN, amount: Long): Location {
        val updated = lpn.copy(quantity = amount)
        return copy(lpnList = lpnList.minus(lpn).plus(updated))
    }

    fun addLocationLPN(lpn: LPN) =
        copy(lpnList = lpnList.plus(lpn))

    fun increaseQuantity(lpn: LPN): Location =
        copy(lpnList = lpnList.minus(lpn).plus(lpn.increaseQuantity()))

    fun hasLpn(lpn: LPN): Boolean {
        return lpnList.any { it.isEqual(lpn) }
    }
}