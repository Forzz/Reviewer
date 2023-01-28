package com.forzz.android.reviewermobile.data.remote.dto

import com.forzz.android.reviewermobile.domain.model.User
import kotlin.math.log

data class UserDTO(
    val login: String,
    val email: String
)

fun UserDTO.toUser(): User {
    return User(
        login = login,
        email = email
    )
}