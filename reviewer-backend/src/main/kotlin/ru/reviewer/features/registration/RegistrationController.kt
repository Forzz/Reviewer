package ru.reviewer.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.reviewer.cache.InMemoryCache
import ru.reviewer.cache.TokenCache
import ru.reviewer.database.tokens.TokenDTO
import ru.reviewer.database.tokens.Tokens
import ru.reviewer.database.users.UserDTO
import ru.reviewer.database.users.Users
import ru.reviewer.utils.isValidEmail
import java.util.*

class RegistrationController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registrationReceiveRemote = call.receive<RegistrationReceiveRemote>()
        if (!registrationReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }
        val userDTO = Users.fetchUser(registrationReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        login = registrationReceiveRemote.login,
                        password = registrationReceiveRemote.password,
                        email = registrationReceiveRemote.email
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            Tokens.insert(
                TokenDTO(
                    rowId = UUID.randomUUID().toString(),
                    login = registrationReceiveRemote.login,
                    token = token
                )
            )

            call.respond(RegistrationResponseRemote(token))
        }
    }
}