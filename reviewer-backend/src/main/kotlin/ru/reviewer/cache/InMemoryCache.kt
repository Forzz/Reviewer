package ru.reviewer.cache

import ru.reviewer.features.registration.RegistrationReceiveRemote

data class TokenCache(
    val login: String,
    val token: String
)

object InMemoryCache {
    val userList: MutableList<RegistrationReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}