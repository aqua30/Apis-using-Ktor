package com.aqua30.domain.manager

import com.aqua30.domain.User

interface DBManager {

    fun addUser(user: User): User

    fun getAllUsers(): List<User>

    fun getById(id: Int): User?

    fun delete(id: Int): Boolean
}