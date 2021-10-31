package com.aqua30

import com.aqua30.dogs.registerDogsRoute
import com.aqua30.plugins.configureMonitoring
import com.aqua30.plugins.configureSerialization
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureMonitoring()
    registerDogsRoute()
}
