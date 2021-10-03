package com.contesini.shopping.service

import com.contesini.shopping.model.User
import com.contesini.shopping.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserServiceTest {

    private lateinit var userService: UserService
    private lateinit var userRepository: UserRepository

    private val id = 1L
    private val name = "User"
    private val email = "user@teste.com"

    @BeforeAll
    fun config() {
        userRepository = mockk()
        userService = UserService(userRepository)
    }

    @Test
    fun getById() {
        val user = createUser()

        every { userRepository.getById(id) } returns user

        val response = userService.getById(id)

        assertThat(response).isNotNull
        assertThat(response.id).isEqualTo(id)
        assertThat(response.name).isEqualTo(name)
        assertThat(response.email).isEqualTo(email)
    }

    @Test
    fun create() {
        val user = User(name = name, email = email)
        val newUser = createUser()

        every { userRepository.save(user) } returns newUser

        val createdUser = userService.create(user)

        assertThat(createdUser).isNotNull
        assertThat(createdUser.id).isEqualTo(newUser.id)
        assertThat(createdUser.name).isEqualTo(newUser.name)
        assertThat(createdUser.email).isEqualTo(newUser.email)
    }

    @Test
    fun update() {
        val oldUser = createUser()
        val newUser = createUser(name = "New User", email = "new_user@teste.com")

        every { userRepository.existsById(id) } returns true
        every { userRepository.save(oldUser) } returns newUser

        val user = userService.update(oldUser)

        assertThat(user).isNotNull
        assertThat(user.id).isEqualTo(oldUser.id)
        assertThat(user.name).isEqualTo(newUser.name)
        assertThat(user.email).isEqualTo(newUser.email)
    }

    @Test
    fun notFoundUserToUpdate() {
        val id = 100L
        val oldUser = createUser(id)

        every { userRepository.existsById(id) } returns false

        val exception = assertThrows<Exception> { userService.update(oldUser) }
        assertThat(exception.message).isEqualTo("User not found")
    }

    @Test
    fun delete() {
        every { userRepository.existsById(id) } returns true
        every { userRepository.deleteById(id) } returns Unit

        val isDeleted = userService.delete(id)

        assertThat(isDeleted).isTrue
    }

    @Test
    fun notFoundToDelete() {
        val id = 100L

        every { userRepository.existsById(id) } returns false

        val isDeleted = userService.delete(id)

        assertThat(isDeleted).isFalse
    }

    private fun createUser(id: Long = this.id, name: String = this.name, email: String = this.email) =
        User(id, name, email)
}