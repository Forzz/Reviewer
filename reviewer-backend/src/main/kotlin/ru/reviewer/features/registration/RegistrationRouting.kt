package ru.reviewer.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.reviewer.cache.InMemoryCache
import ru.reviewer.cache.TokenCache
import ru.reviewer.features.login.LoginResponseRemote
import ru.reviewer.utils.isValidEmail
import java.util.*

fun Application.configureRegistrationRouting() {

    routing {
        post("/registration") {
            val registrationController = RegistrationController(call)
            registrationController.registerNewUser()
        }
    }
}