package com.aqua30.database

import com.aqua30.database.common.DbConstants.KEY_DB_PWD
import com.aqua30.database.common.DbConstants.KEY_DB_URL
import com.aqua30.database.common.DbConstants.KEY_DB_USER
import io.ktor.server.application.*
import org.ktorm.database.Database

object DBHelper {

    private var dbUrl = ""
    private var dbUser = ""
    private var dbPwd = ""

    fun Application.configureDbVariables() {
        dbUrl = environment.config.propertyOrNull(KEY_DB_URL)?.getString() ?: ""
        dbUser = environment.config.propertyOrNull(KEY_DB_USER)?.getString() ?: ""
        dbPwd = environment.config.propertyOrNull(KEY_DB_PWD)?.getString() ?: ""
    }

    fun database() = Database.connect(
        dbUrl,
        user = dbUser,
        password = dbPwd
    )
}