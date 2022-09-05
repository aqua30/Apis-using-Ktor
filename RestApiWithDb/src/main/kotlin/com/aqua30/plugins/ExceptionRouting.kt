package com.aqua30.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureExceptionRoutes() {
    routing {
        route("/exception") {
            exceptionRoutes()
            statusRoutes()
        }
    }
}

fun Route.exceptionRoutes() {
    get("/validation") {
        throw ValidationException("this is a validation exception")
    }

    get("/parsing") {
        throw ParsingException("this is a parsing exception")
    }
}

fun Route.statusRoutes() {
    get("/internal-error") {
        call.respond(
            HttpStatusCode.InternalServerError
        )
    }

    get("/bad-gateway") {
        call.respond(
            HttpStatusCode.BadGateway
        )
    }
}

