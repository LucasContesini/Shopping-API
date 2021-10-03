package com.contesini.shopping.controller

import com.contesini.shopping.model.Product
import com.contesini.shopping.service.ProductService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProductControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc
    private lateinit var productController: ProductController
    private lateinit var productService: ProductService

    @BeforeAll
    fun config() {
        productService = mockk()
        productController = ProductController(productService)
    }

    @Test
    fun getAll() {
        val products = listOf(createProduct(), createProduct(2, "Shorts"))

        every { productService.getAll() } returns products

        mvc.perform(
            MockMvcRequestBuilders.get("/products")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)

    }

    @Test
    @Disabled
    fun getById() {
        val id: Long = 1
        val product = createProduct()

        every { productService.getById(id) } returns product

        mvc.perform(
            MockMvcRequestBuilders.get("/product/${id}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun create() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }


    private fun createProduct(id: Long = 1, name: String = "Camiseta") = Product(id, name)
}