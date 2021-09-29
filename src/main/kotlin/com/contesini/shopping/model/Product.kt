package com.contesini.shopping.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "product")
data class Product(@Id @GeneratedValue val id: Long = 0, val name: String) {

}
