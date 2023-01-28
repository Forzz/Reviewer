package ru.reviewer.features.shows

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.reviewer.database.movies.Movies
import ru.reviewer.database.shows.Shows
import ru.reviewer.database.shows.mapToCreateShowResponse
import ru.reviewer.database.shows.mapToShowDTO
import ru.reviewer.features.movies.models.DeleteMovieRequest
import ru.reviewer.features.movies.models.DeleteMovieResponse
import ru.reviewer.features.shows.models.CreateShowRequest
import ru.reviewer.features.shows.models.DeleteShowRequest
import ru.reviewer.features.shows.models.DeleteShowResponse

class ShowsController(private val call: ApplicationCall) {

    suspend fun fetchAllShows() {
        call.respond(Shows.fetchShows())
    }

    suspend fun addShow() {
        val request = call.receive<CreateShowRequest>()
        val show = request.mapToShowDTO()
        Shows.insert(show)
        call.respond(show.mapToCreateShowResponse())
    }

    suspend fun deleteShow() {
        val request = call.receive<DeleteShowRequest>()
        val showID = request.id
        Shows.remove(showID)
        call.respond(DeleteShowResponse("Deleted $showID"))
    }
}