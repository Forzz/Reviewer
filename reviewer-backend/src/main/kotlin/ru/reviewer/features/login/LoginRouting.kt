package ru.reviewer.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.reviewer.cache.InMemoryCache
import ru.reviewer.cache.TokenCache
import java.util.UUID

fun Application.configureLoginRouting() {

    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }

        post("/user/fetch") {
            val loginController = LoginController(call)
            loginController.fetchUserData()
        }
    }
}