package ru.reviewer.features.games

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.reviewer.database.games.Games
import ru.reviewer.database.games.mapToCreateGameResponse
import ru.reviewer.database.games.mapToGameDTO
import ru.reviewer.database.movies.Movies
import ru.reviewer.database.movies.mapToCreateMovieResponse
import ru.reviewer.database.movies.mapToMovieDTO
import ru.reviewer.features.games.models.CreateGameRequest
import ru.reviewer.features.games.models.DeleteGameRequest
import ru.reviewer.features.games.models.DeleteGameResponse
import ru.reviewer.features.movies.models.CreateMovieRequest
import ru.reviewer.features.movies.models.DeleteMovieRequest
import ru.reviewer.features.movies.models.DeleteMovieResponse

class GamesController(private val call: ApplicationCall) {

    suspend fun fetchAllGames() {
        call.respond(Games.fetchGames())
    }

    suspend fun addGame() {
        val request = call.receive<CreateGameRequest>()
        val game = request.mapToGameDTO()
        Games.insert(game)
        call.respond(game.mapToCreateGameResponse())
    }

    suspend fun deleteGame() {
        val request = call.receive<DeleteGameRequest>()
        val gameID = request.id
        Games.remove(gameID)
        call.respond(DeleteGameResponse("Deleted $gameID"))
    }
}