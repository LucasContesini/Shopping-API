package com.contesini.shopping.service

import com.contesini.shopping.model.Product
import com.contesini.shopping.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {


    fun getAll(): List<Product> = productRepository.findAll()

    fun getById(id: Long): Product = productRepository.getById(id)

    fun create(product: Product): Product = productRepository.save(product)

    fun update(product: Product): Product {
        if (productRepository.existsById(product.id)) {
            return productRepository.save(product)
        }
        throw Exception("Product not found")
    }

    fun delete(id: Long): Boolean {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
            return true
        }
        return false
    }
}
