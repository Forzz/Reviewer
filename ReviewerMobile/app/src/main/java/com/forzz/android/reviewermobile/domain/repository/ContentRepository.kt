package com.forzz.android.reviewermobile.domain.repository

import com.forzz.android.reviewermobile.data.remote.SignInRequest
import com.forzz.android.reviewermobile.data.remote.SignInResponse
import com.forzz.android.reviewermobile.data.remote.dto.MovieDTO
import com.forzz.android.reviewermobile.data.remote.dto.UserDTO
import com.forzz.android.reviewermobile.domain.model.Movie
import com.forzz.android.reviewermobile.domain.model.User
import io.reactivex.Single

interface ContentRepository {

    fun getMovies(): Single<List<MovieDTO>>
    fun performLogin(signInRequest: SignInRequest): Single<SignInResponse>
    fun getToken(): String?
    fun saveUserToken(token: String)
}