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
            getDogsRoute()
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
    get("{id}") {
        val id = call.parameters["id"]?.toInt()
        val dog = dogs.find { it.id == id }
        dog?.let {
            call.respond(HttpStatusCode.Found, it)
        } ?: call.respond(HttpStatusCode.NotFound, "No dog found with id $id")

    }
}

fun Route.getDogsRoute() {
    get {
        if (dogs.isNotEmpty())
            call.respond(HttpStatusCode.OK, dogs)
        else
            call.respond(HttpStatusCode.NotFound, "No dog found")
    }
}

fun Route.deleteDogRoute() {
    delete("{id}") {
        val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(
            HttpStatusCode.BadRequest,
            "Dog id required"
        )
        if (dogs.removeIf { it.id == id })
            call.respond(HttpStatusCode.Accepted, "Dog removed successfully")
        else
            call.respond(HttpStatusCode.NotFound, "No dog found with id $id")
    }
}

@Serializable
data class Dog(val name: String, val color: String = "Unknown", val id: Int = 0)