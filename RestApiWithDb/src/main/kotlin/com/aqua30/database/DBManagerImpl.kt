package com.aqua30.database

import com.aqua30.database.entities.UserTable
import com.aqua30.domain.User
import com.aqua30.domain.manager.DBManager
import org.ktorm.database.asIterable
import org.ktorm.dsl.*

class DBManagerImpl: DBManager {

    private val database = DBHelper.database()

    override fun addUser(user: User): User {
        database.insert(UserTable) {
            set(it.first_name, user.first_name)
            set(it.last_name, user.last_name)
            set(it.dob, user.dob)
            set(it.age, user.age)
            set(it.city, user.city)
        }
        return user
    }

    override fun getAllUsers(): List<User> {
        val dbUser = database.from(UserTable).select()
        return dbUser.rowSet.asIterable().map { row ->
            User(
                row[UserTable.id] ?: -1,
                "${row[UserTable.first_name] ?: ""}",
                "${row[UserTable.last_name] ?: ""}",
                row[UserTable.age] ?: -1,
                row[UserTable.dob] ?: "",
                row[UserTable.city] ?: ""
            )
        }
    }

    override fun getById(id: Int): User? {
        val dbUser = database.from(UserTable).select().where { UserTable.id eq id }
        val row = dbUser.rowSet.asIterable().firstOrNull()
        return row?.let {
            User(
                it[UserTable.id] ?: -1,
                "${it[UserTable.first_name] ?: ""}",
                "${it[UserTable.last_name] ?: ""}",
                it[UserTable.age] ?: -1,
                it[UserTable.dob] ?: "",
                it[UserTable.city] ?: ""
            )
        }?: null
    }

    override fun delete(id: Int): Boolean {
        val affectedRow = database.delete(UserTable) { it.id eq id }
        return affectedRow == 1
    }

}