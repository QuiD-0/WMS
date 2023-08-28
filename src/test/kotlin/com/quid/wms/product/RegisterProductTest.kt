package com.quid.wms.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RegisterProductTest {

    private lateinit var registerProduct: RegisterProduct
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        productRepository = ProductRepository()
        registerProduct = RegisterProduct(productRepository)
    }

    @Test
    @DisplayName("상품을 등록한다.")
    fun registerProduct() {
        val request = RegisterProductRequest(
            "name",
            "code",
            "description",
            "brand",
            "maker",
            "origin",
            Category.ELECTRONICS,
            TemperatureZone.ROOM_TEMPERATURE,
            1000L,
            100L,
            100L,
            100L
        )
        registerProduct.request(request)

        assertThat(productRepository.findAll()).hasSize(1)
    }
}

class RegisterProduct(
    private val productRepository: ProductRepository
) {
    fun request(request: RegisterProductRequest) {
        val product = request.toDomain()
        productRepository.save(product)
    }
}

data class RegisterProductRequest(
    val name: String,
    val code: String,
    val description: String,
    val brand: String,
    val maker: String,
    val origin: String,
    val category: Category,
    val temperatureZone: TemperatureZone,
    val weight: Long,
    val widthInMillimeters: Long,
    val heightInMillimeters: Long,
    val lengthInMillimeters: Long,
) {
    init {
        require(name.isNotBlank()) { "상품명은 필수입니다." }
        require(code.isNotBlank()) { "상품 코드는 필수입니다." }
        require(description.isNotBlank()) { "상품 설명은 필수입니다." }
        require(brand.isNotBlank()) { "상품 브랜드는 필수입니다." }
        require(maker.isNotBlank()) { "상품 제조사는 필수입니다." }
        require(origin.isNotBlank()) { "상품 원산지는 필수입니다." }
        require(weight > 0) { "상품 무게는 0보다 커야합니다." }
        require(widthInMillimeters > 0) { "상품 가로 길이는 0보다 커야합니다." }
        require(heightInMillimeters > 0) { "상품 세로 길이는 0보다 커야합니다." }
        require(lengthInMillimeters > 0) { "상품 세로 길이는 0보다 커야합니다." }
    }

    fun toDomain() = Product(
        1L, name, code, description, brand, maker, origin, category, temperatureZone, weight, ProductSize(
            widthInMillimeters, heightInMillimeters, lengthInMillimeters
        )
    )
}

enum class Category(
    val description: String
) {
    ELECTRONICS("전자 제품")
}

enum class TemperatureZone(
    val description: String
) {
    ROOM_TEMPERATURE("상온"),
}

data class Product(
    val id: Long,
    val name: String,
    val code: String,
    val description: String,
    val brand: String,
    val maker: String,
    val origin: String,
    val category: Category,
    val temperatureZone: TemperatureZone,
    val weight: Long,
    val size: ProductSize,
)

data class ProductSize(
    val widthInMillimeters: Long,
    val heightInMillimeters: Long,
    val lengthInMillimeters: Long,
)

class ProductRepository {
    private val products = mutableMapOf<Long, Product>()

    fun findAll(): List<Product> {
        return products.values.toList()
    }

    fun save(product: Product) {
        products[product.id] = product
    }
}