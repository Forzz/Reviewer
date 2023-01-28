package ru.reviewer.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.reviewer.database.users.Users

object Tokens : Table() {
    private val id = Tokens.varchar("id", 75)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 75)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[id] = tokenDTO.rowId
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchTokens(): List<TokenDTO> {
        return try {
            transaction {
                Tokens.selectAll().toList().map {
                    TokenDTO(rowId = it[Tokens.id], token = it[Tokens.token], login = it[Tokens.login])
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}