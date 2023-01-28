package ru.reviewer.database.shows

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.reviewer.database.movies.Movies
import ru.reviewer.database.tokens.TokenDTO
import ru.reviewer.database.tokens.Tokens

object Shows : Table() {
    private val id = Shows.varchar("id", 75)
    private val title = Shows.varchar("title", 75)
    private val date = Shows.varchar("date", 20)
    private val length = Shows.varchar("length", 10)
    private val genre = Shows.varchar("genre", 15)
    private val imdb = Shows.varchar("imdb", 6)
    private val description = Shows.varchar("description", 600)
    private val imageUrl = Shows.varchar("image_url", 100)

    fun insert(showDTO: ShowDTO) {
        transaction {
            Shows.insert {
                it[id] = showDTO.id
                it[title] = showDTO.title
                it[date] = showDTO.date
                it[length] = showDTO.length
                it[genre] = showDTO.genre
                it[imdb] = showDTO.imdb
                it[description] = showDTO.description
                it[imageUrl] = showDTO.imageUrl
            }
        }
    }

    fun remove(id: String) {
        transaction {
            Shows.deleteWhere { Shows.id eq id }
        }
    }

    fun fetchShows(): List<ShowDTO> {
        return try {
            transaction {
                Shows.selectAll().toList().map {
                    ShowDTO(
                        id = it[Shows.id],
                        title = it[Shows.title],
                        date = it[Shows.date],
                        length = it[Shows.length],
                        genre = it[Shows.genre],
                        imdb = it[Shows.imdb],
                        description = it[Shows.description],
                        imageUrl = it[Shows.imageUrl]
                    )
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}