package com.quid.wms.location.domain

data class Location(
    val id: Long?,
    val locationBarcode: String,
    val storageType: StorageType,
    val usagePurpose: UsagePurpose,
    val lpnList: List<LPN> = listOf(),
) {
    fun assignLPN(lpn: LPN): Location {
        findLPN(lpn)
            ?.let { return increaseQuantity(it) }
            ?: return addLocationLPN(lpn)
    }

    fun updateAmount(lpn: LPN, amount: Long): Location {
        val locationLpn = findLPN(lpn)?: throw IllegalArgumentException("lpn not found")
        val updated = locationLpn.copy(quantity = amount)
        return copy(lpnList = lpnList.minus(locationLpn).plus(updated))
    }

    private fun addLocationLPN(lpn: LPN) =
        copy(lpnList = lpnList.plus(lpn))

    private fun increaseQuantity(lpn: LPN): Location =
        copy(lpnList = lpnList.minus(lpn).plus(lpn.increaseQuantity()))

    private fun findLPN(lpn: LPN): LPN? = lpnList.find { it.isEqual(lpn) }
}