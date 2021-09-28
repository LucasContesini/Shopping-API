package com.contesini.shopping.controller

import com.contesini.shopping.model.Product
import com.contesini.shopping.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

    @GetMapping("/products")
    fun getAll(): List<Product> = productService.getAll()

    @GetMapping("/product/{id}")
    fun getById(@PathVariable id: Long): Product = productService.getById(id)

    @PostMapping("/product")
    fun create(@RequestBody product: Product) = productService.create(product)

    @PutMapping("/product")
    fun update(@RequestBody product: Product) = productService.update(product)

    @DeleteMapping("/product/{id}")
    fun delete(@PathVariable id: Long): Boolean = productService.delete(id)
}