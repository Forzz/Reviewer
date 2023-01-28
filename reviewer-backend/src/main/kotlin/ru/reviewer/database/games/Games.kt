package ru.reviewer.database.games

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Games : Table() {
    private val id = Games.varchar("id", 75)
    private val title = Games.varchar("title", 75)
    private val date = Games.varchar("date", 20)
    private val developer = Games.varchar("developer", 50)
    private val genre = Games.varchar("genre", 15)
    private val metacritic = Games.varchar("metacritic", 6)
    private val description = Games.varchar("description", 600)
    private val imageUrl = Games.varchar("image_url", 100)

    fun insert(gameDTO: GameDTO) {
        transaction {
            Games.insert {
                it[id] = gameDTO.id
                it[title] = gameDTO.title
                it[date] = gameDTO.date
                it[developer] = gameDTO.developer
                it[genre] = gameDTO.genre
                it[metacritic] = gameDTO.metacritic
                it[description] = gameDTO.description
                it[imageUrl] = gameDTO.imageUrl
            }
        }
    }

    fun remove(id: String) {
        transaction {
            Games.deleteWhere { Games.id eq id }
        }
    }

    fun fetchGames(): List<GameDTO> {
        return try {
            transaction {
                Games.selectAll().toList().map {
                    GameDTO(
                        id = it[Games.id],
                        title = it[Games.title],
                        date = it[Games.date],
                        developer = it[Games.developer],
                        genre = it[Games.genre],
                        metacritic = it[Games.metacritic],
                        description = it[Games.description],
                        imageUrl = it[Games.imageUrl]
                    )
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}