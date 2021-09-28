package com.contesini.shopping.service

import com.contesini.shopping.model.User
import com.contesini.shopping.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun getById(id: Long): User = userRepository.getById(id)

    fun create(user: User): User = userRepository.save(user)

    fun update(user: User): User {
        if (userRepository.existsById(user.id)) {
            return userRepository.save(user)
        }

        throw Exception("User not found")
    }

    fun delete(id: Long): Boolean {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            return true
        }
        return false
    }
}