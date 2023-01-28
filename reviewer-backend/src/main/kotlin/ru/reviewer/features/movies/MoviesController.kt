package ru.reviewer.features.movies

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.reviewer.database.movies.Movies
import ru.reviewer.database.movies.mapToCreateMovieResponse
import ru.reviewer.database.movies.mapToMovieDTO
import ru.reviewer.features.movies.models.CreateMovieRequest
import ru.reviewer.features.movies.models.DeleteMovieRequest
import ru.reviewer.features.movies.models.DeleteMovieResponse

class MoviesController(private val call: ApplicationCall) {

    suspend fun fetchAllMovies() {
        call.respond(Movies.fetchMovies())
    }

    suspend fun addMovie() {
        val request = call.receive<CreateMovieRequest>()
        val movie = request.mapToMovieDTO()
        Movies.insert(movie)
        call.respond(movie.mapToCreateMovieResponse())
    }

    suspend fun deleteMovie() {
        val request = call.receive<DeleteMovieRequest>()
        val movieID = request.id
        Movies.remove(movieID)
        call.respond(DeleteMovieResponse("Deleted $movieID"))
    }
}