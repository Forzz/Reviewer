package ru.reviewer.features.movies

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureMovieRouting() {

    routing {
        get("/movies/fetch") {
            MoviesController(call).fetchAllMovies()
        }

        post("/movies/add") {
            MoviesController(call).addMovie()
        }

        delete("/movies/delete") {
            MoviesController(call).deleteMovie()
        }
    }
}