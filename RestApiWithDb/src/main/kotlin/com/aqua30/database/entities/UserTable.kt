package com.aqua30.database.entities

import com.aqua30.database.common.DbConstants.TABLE_USER_DETAIL
import com.aqua30.domain.User
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserTable: Table<Nothing>(TABLE_USER_DETAIL) {

    val id = int("id").primaryKey()
    val first_name = varchar("first_name")
    val last_name = varchar("last_name")
    val age = int("age")
    val dob = varchar("dob")
    val city = varchar("city")
    val country = varchar("country")
    val address = varchar("address")
    val pincode = varchar("pincode")
    val pan = varchar("pan")
    val aadhar = varchar("aadhar")
    val company_name = varchar("company_name")
    val company_location = varchar("company_location")
    val company_address = varchar("company_address")
}