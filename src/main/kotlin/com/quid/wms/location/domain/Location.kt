package com.quid.wms.location.domain

import com.quid.wms.outbound.domain.ProductQuantity

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

    fun hasProduct(productId: Long): Boolean = lpnList.any { it.productId == productId }
    fun getLpn(productId: Long): LPN {
        return lpnList.find { it.productId == productId }!!
    }

    companion object {
        fun getStockQuantity(locationList: List<Location>): List<ProductQuantity> =
            locationList.flatMap { it.lpnList }
                .groupBy { it.productId }
                .mapValues { it.value.sumOf { lpn -> lpn.quantity } }
                .map { ProductQuantity(it.key, it.value) }

    }
}