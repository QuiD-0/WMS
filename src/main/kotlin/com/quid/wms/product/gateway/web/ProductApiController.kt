package com.quid.wms.product.gateway.web

import com.quid.wms.product.gateway.repository.ProductRepository
import com.quid.wms.product.gateway.web.dto.RegistProductRequest
import com.quid.wms.product.usecase.RegistProduct
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductApiController(
    private val registProduct: RegistProduct
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun request(@RequestBody request: RegistProductRequest) {
        val product = request.toDomain()
        registProduct.register(product)
    }
}