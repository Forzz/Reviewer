package ru.reviewer

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.reviewer.features.games.configureGameRouting
import ru.reviewer.features.login.configureLoginRouting
import ru.reviewer.features.movies.configureMovieRouting
import ru.reviewer.features.registration.configureRegistrationRouting
import ru.reviewer.features.shows.configureShowRouting
import ru.reviewer.plugins.configureRouting
import ru.reviewer.plugins.configureSerialization

fun main() {
    val postgresUser = System.getenv("postgres_user")
    val postgresPassword = System.getenv("postgres_password")

    Database.connect(
        "jdbc:postgresql://localhost:5432/reviewer",
        driver = "org.postgresql.Driver",
        user = postgresUser,
        password = postgresPassword
    )

    embeddedServer(CIO, port = 8081, host = "0.0.0.0") {
        configureSerialization()
        configureLoginRouting()
        configureRegistrationRouting()
        configureMovieRouting()
        configureShowRouting()
        configureGameRouting()
        configureRouting()
    }.start(wait = true)
}
