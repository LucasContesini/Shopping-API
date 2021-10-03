package com.contesini.shopping.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(@Id @GeneratedValue val id: Long = 0L, val name: String, val email: String)