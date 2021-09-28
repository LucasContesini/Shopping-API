package com.contesini.shopping.repository

import com.contesini.shopping.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository

@Repository
@EnableJpaRepositories
interface UserRepository : JpaRepository<User, Long> {
}