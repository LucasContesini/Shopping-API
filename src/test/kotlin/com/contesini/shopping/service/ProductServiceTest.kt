package com.contesini.shopping.service

import com.contesini.shopping.model.Product
import com.contesini.shopping.repository.ProductRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProductServiceTest {

    private lateinit var productService: ProductService
    private lateinit var productRepository: ProductRepository

    private val id = 1L
    private val name = "Camiseta"

    @BeforeAll
    fun config() {
        productRepository = mockk()
        productService = ProductService(productRepository)
    }

    @Test
    fun getAll() {
        every { productRepository.findAll() } returns listOf(createProduct(), createProduct(2, "Shorts"))

        val all = productService.getAll()

        assertThat(all.size).isEqualTo(2)
    }

    @Test
    fun getById() {
        every { productRepository.getById(id) } returns createProduct()

        val product = productService.getById(id)

        assertThat(product).isNotNull
        assertThat(product.id).isEqualTo(id)
        assertThat(product.name).isEqualTo(name)
    }

    @Test
    fun create() {
        val product = createProduct(name = name)
        val newProduct = createProduct()

        every { productRepository.save(product) } returns newProduct

        val createProduct = productService.create(product)

        assertThat(createProduct).isNotNull
        assertThat(createProduct.id).isEqualTo(newProduct.id)
        assertThat(createProduct.name).isEqualTo(newProduct.name)
    }

    @Test
    fun update() {
        val name = "Produto Novo"
        val oldProduct = createProduct()
        val newProduct = createProduct(name = name)

        every { productRepository.existsById(id) } returns true
        every { productRepository.save(oldProduct) } returns newProduct

        val product = productService.update(oldProduct)

        assertThat(product).isNotNull
        assertThat(product.id).isEqualTo(oldProduct.id)
        assertThat(product.name).isEqualTo(name)
    }

    @Test
    fun notFoundProductToUpdate() {
        val id = 100L
        val oldName = "Produto Antigo"
        val oldProduct = createProduct(id, oldName)

        every { productRepository.existsById(id) } returns false

        val exception = assertThrows<Exception> { productService.update(oldProduct) }
        assertThat(exception.message).isEqualTo("Product not found")
    }

    @Test
    fun delete() {
        every { productRepository.existsById(id) } returns true
        every { productRepository.deleteById(id) } returns Unit

        val isDeleted = productService.delete(id)

        assertThat(isDeleted).isTrue
    }

    @Test
    fun notFoundProductToDelete() {
        val id = 100L

        every { productRepository.existsById(id) } returns false

        val isDeleted = productService.delete(id)

        assertThat(isDeleted).isFalse
    }

    private fun createProduct(id: Long = this.id, name: String = this.name) = Product(id, name)
}