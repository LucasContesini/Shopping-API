package com.contesini.shopping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ShoppingApplication

fun main(args: Array<String>) {
	runApplication<ShoppingApplication>(*args)
}
