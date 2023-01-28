package ru.reviewer.features.shows

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.reviewer.features.movies.MoviesController

fun Application.configureShowRouting() {

    routing {
        get("/shows/fetch") {
            ShowsController(call).fetchAllShows()
        }

        post("/shows/add") {
            ShowsController(call).addShow()
        }

        delete("/shows/delete") {
            ShowsController(call).deleteShow()
        }
    }
}