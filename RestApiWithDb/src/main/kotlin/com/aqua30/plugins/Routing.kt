package com.aqua30.plugins

import com.aqua30.database.UserDBRepository
import com.aqua30.domain.User
import com.aqua30.domain.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*


fun Application.configureItemRoutes() {
    val repository: UserRepository = UserDBRepository()
    routing {
        route("/users") {
            addUser(repository)
            getUsers(repository)
            getUserById(repository)
            deleteUser(repository)
        }
    }
}

fun Route.addUser(repository: UserRepository) {
    post {
        val request = call.receive<User>()
        val addedUser = repository.addUser(request)
        call.respond(
            HttpStatusCode.Created,
            addedUser
        )
    }
}

fun Route.getUserById(repository: UserRepository) {
    get("{id}") {
        val id = call.parameters["id"]?.toInt() ?: 0
        val user = repository.getById(id)
        user?.let {
            call.respond(HttpStatusCode.Found, it)
        }?: call.respond(HttpStatusCode.NotFound,"user not found with id $id")
    }
}

fun Route.getUsers(repository: UserRepository) {
    get {
        val users = repository.getAllUsers()
        call.respond(
            HttpStatusCode.OK,
            users
        )
    }
}

fun Route.deleteUser(repository: UserRepository) {
    delete("{id}") {
        val id = call.parameters["id"]?.toInt() ?: 0
        val result = repository.delete(id)
        call.respond(
            if (result) HttpStatusCode.OK else HttpStatusCode.NotFound,
            if (result) "User with id $id deleted" else "User with id $id not found"
        )
    }
}