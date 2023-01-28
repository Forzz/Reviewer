package ru.reviewer.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.reviewer.database.tokens.TokenDTO
import ru.reviewer.database.tokens.Tokens
import ru.reviewer.database.users.UserDTO
import ru.reviewer.database.users.Users
import ru.reviewer.features.login.models.FetchUserDataRequest
import ru.reviewer.features.login.models.UserDataResponse
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        rowId = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )

                call.respond(LoginResponseRemote(token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid Password")
            }
        }
    }

    suspend fun fetchUserData() {
        val receive = call.receive<FetchUserDataRequest>()
        val tokens = Tokens.fetchTokens()


        val login = tokens.find { tokenDTO -> receive.token.equals(tokenDTO.token) }?.login
        var userData = login?.let { Users.fetchUser(it) }

        if (userData != null) {
            call.respond(UserDataResponse(userData.login, userData.email))
        } else {
            call.respond(HttpStatusCode.BadRequest, "Error")
        }
    }
}