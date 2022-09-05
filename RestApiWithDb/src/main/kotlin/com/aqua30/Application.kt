package com.aqua30

import com.aqua30.database.DBHelper.configureDbVariables
import com.aqua30.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureDbVariables()
    configureExceptions()
    configureExceptionRoutes()
    configureMonitoring()
    configureSerialization()
    configureItemRoutes()
}
