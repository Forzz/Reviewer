package ru.reviewer.database.tokens

import ru.reviewer.database.shows.ShowDTO
import ru.reviewer.database.users.UserDTO
import ru.reviewer.database.users.Users
import ru.reviewer.features.login.models.FetchUserDataRequest
import ru.reviewer.features.shows.models.CreateShowResponse

@kotlinx.serialization.Serializable
class TokenDTO(val rowId: String, val login: String, val token: String)
