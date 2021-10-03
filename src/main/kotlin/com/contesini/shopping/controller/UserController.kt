package com.contesini.shopping.controller

import com.contesini.shopping.model.User
import com.contesini.shopping.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = userService.getById(id)

    @PostMapping
    fun create(@RequestBody user: User) = userService.create(user)

    @PutMapping
    fun update(@RequestBody user: User) = userService.update(user)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = userService.delete(id)
}