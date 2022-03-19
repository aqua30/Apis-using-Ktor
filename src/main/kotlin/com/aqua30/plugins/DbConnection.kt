package com.aqua30.plugins

import io.ktor.application.*
import org.ktorm.database.Database

fun Application.configureDatabase() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/dogs_db",
        user = "root",
        password = "xelba$%123"
    )
}