package com.aqua30.dogs

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable

val dogs = mutableListOf<Dog>()

fun Application.registerDogsRoute() {
    routing {
        route("/dogs") {
            addDogRoute()
            getDogRoute()
            deleteDogRoute()
        }
    }
}

fun Route.addDogRoute() {
    post {
        val dog = call.receive<Dog>()
        dogs.add(dog)
        call.respond(HttpStatusCode.Created, "Dog saved")
    }
}

fun Route.getDogRoute() {
    get {
        if (dogs.isNotEmpty())
            call.respond(HttpStatusCode.OK, dogs)
        else
            call.respond(HttpStatusCode.NotFound, "No dog found")
    }
}

fun Route.deleteDogRoute() {
    delete("{id}") {
        val dogId = call.parameters["id"]?.toInt() ?: return@delete call.respond(
            HttpStatusCode.BadRequest,
            "Dog id required"
        )
        if (dogs.removeIf { it.id == dogId })
            call.respond(HttpStatusCode.Accepted, "Dog removed successfully")
        else
            call.respond(HttpStatusCode.NotFound, "Dog is not found")
    }
}

@Serializable
data class Dog(val name: String, val color: String = "Unknown", val id: Int = 0)