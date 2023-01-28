package ru.reviewer.features.games

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.reviewer.features.movies.MoviesController

fun Application.configureGameRouting() {

    routing {
        get("/games/fetch") {
            GamesController(call).fetchAllGames()
        }

        post("/games/add") {
            GamesController(call).addGame()
        }

        delete("/games/delete") {
            GamesController(call).deleteGame()
        }
    }
}