package com.aqua30.database

import com.aqua30.domain.User
import com.aqua30.domain.manager.DBManager
import com.aqua30.domain.repository.UserRepository

class UserDBRepository : UserRepository {

    private val database: DBManager = DBManagerImpl()

    override fun addUser(user: User): User {
        database.addUser(user)
        return user
    }

    override fun getAllUsers(): List<User> {
        return database.getAllUsers()
    }

    override fun getById(id: Int): User? {
        return database.getById(id)
    }

    override fun delete(id: Int): Boolean {
        return database.delete(id)
    }
}