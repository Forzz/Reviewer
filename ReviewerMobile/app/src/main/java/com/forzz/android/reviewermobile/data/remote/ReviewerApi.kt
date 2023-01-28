package com.forzz.android.reviewermobile.data.remote

import com.forzz.android.reviewermobile.data.remote.dto.MovieDTO
import com.forzz.android.reviewermobile.data.remote.dto.UserDTO
import com.forzz.android.reviewermobile.domain.model.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReviewerApi {

    @GET("/movies/fetch")
    fun getMovies(): Single<List<MovieDTO>>

    @POST("/login")
    fun performLogin(@Body signInRequest: SignInRequest): Single<SignInResponse>
}