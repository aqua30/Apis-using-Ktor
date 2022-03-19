package com.aqua30.repository.database.schemas

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Dog: Table<Nothing>("t_dog") {
    val id = int("_id").primaryKey()
    val petName = varchar("name")
    val color = varchar("color")
    val breed = varchar("breed")
}