package com.aqua30.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable

fun Application.configureExceptions() {
    install(StatusPages) {
        exception<Throwable> { call, throwable ->
            when(throwable) {
                is ValidationException -> {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ExceptionResponse("${throwable.message}", HttpStatusCode.BadRequest.value)
                    )
                }
                is ParsingException -> {
                    call.respond(
                        HttpStatusCode.NotFound,
                        ExceptionResponse("${throwable.message}",HttpStatusCode.ExpectationFailed.value)
                    )
                }
            }
        }

        status(
            HttpStatusCode.InternalServerError,
            HttpStatusCode.BadGateway,
        ) { call, statusCode ->
            when(statusCode) {
                HttpStatusCode.InternalServerError -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        ExceptionResponse("Oops! internal server error at our end", HttpStatusCode.InternalServerError.value)
                    )
                }
                HttpStatusCode.BadGateway -> {
                    call.respond(
                        HttpStatusCode.BadGateway,
                        ExceptionResponse("Oops! We got a bad gateway. Fixing it. Hold on!", HttpStatusCode.BadGateway.value)
                    )
                }
            }
        }
    }
}

class ValidationException(override val message: String): Throwable()
class ParsingException(override val message: String): Throwable()

@Serializable
data class ExceptionResponse(
    val message: String,
    val code: Int
)