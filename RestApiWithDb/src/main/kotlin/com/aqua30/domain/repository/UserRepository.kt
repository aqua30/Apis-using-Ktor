package com.aqua30.domain.repository

import com.aqua30.domain.User

interface UserRepository {

    fun addUser(user: User): User

    fun getAllUsers(): List<User>

    fun getById(id: Int): User?

    fun delete(id: Int): Boolean
}