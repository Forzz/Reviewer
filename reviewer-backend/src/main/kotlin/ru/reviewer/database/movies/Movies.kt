package ru.reviewer.database.movies

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Movies : Table() {
    private val id = Movies.varchar("id", 75)
    private val title = Movies.varchar("title", 75)
    private val date = Movies.varchar("date", 20)
    private val length = Movies.varchar("length", 10)
    private val genre = Movies.varchar("genre", 15)
    private val imdb = Movies.varchar("imdb", 6)
    private val description = Movies.varchar("description", 600)
    private val imageUrl = Movies.varchar("image_url", 100)

    fun insert(movieDTO: MovieDTO) {
        transaction {
            Movies.insert {
                it[id] = movieDTO.id
                it[title] = movieDTO.title
                it[date] = movieDTO.date
                it[length] = movieDTO.length
                it[genre] = movieDTO.genre
                it[imdb] = movieDTO.imdb
                it[description] = movieDTO.description
                it[imageUrl] = movieDTO.imageUrl
            }
        }
    }

    fun remove(id: String) {
        transaction {
            Movies.deleteWhere { Movies.id eq id }
        }
    }

    fun fetchMovies(): List<MovieDTO> {
        return try {
            transaction {
                Movies.selectAll().toList().map {
                    MovieDTO(
                        id = it[Movies.id],
                        title = it[Movies.title],
                        date = it[Movies.date],
                        length = it[Movies.length],
                        genre = it[Movies.genre],
                        imdb = it[Movies.imdb],
                        description = it[Movies.description],
                        imageUrl = it[Movies.imageUrl]
                    )
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}