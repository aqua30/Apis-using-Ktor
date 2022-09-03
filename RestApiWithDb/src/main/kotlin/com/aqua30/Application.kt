package com.aqua30

import com.aqua30.database.DBHelper.configureDbVariables
import com.aqua30.plugins.configureItemRoutes
import com.aqua30.plugins.configureMonitoring
import com.aqua30.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureDbVariables()
    configureMonitoring()
    configureSerialization()
    configureItemRoutes()
}
