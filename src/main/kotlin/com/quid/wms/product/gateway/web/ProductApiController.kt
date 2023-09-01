package com.quid.wms.product.gateway.web

import com.quid.wms.product.domain.Product
import com.quid.wms.product.gateway.web.request.RegistProductRequest
import com.quid.wms.product.usecase.RegistProduct
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductApiController(
    private val registProduct: RegistProduct
) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun request(@RequestBody request: RegistProductRequest): Product =
        registProduct.register(request.toDomain())
}