package com.forzz.android.reviewermobile.data.repository

import com.forzz.android.reviewermobile.data.preferences.PreferenceProvider
import com.forzz.android.reviewermobile.data.remote.ReviewerApi
import com.forzz.android.reviewermobile.data.remote.SignInRequest
import com.forzz.android.reviewermobile.data.remote.SignInResponse
import com.forzz.android.reviewermobile.data.remote.dto.MovieDTO
import com.forzz.android.reviewermobile.data.remote.dto.UserDTO
import com.forzz.android.reviewermobile.domain.model.User
import com.forzz.android.reviewermobile.domain.repository.ContentRepository
import io.reactivex.Single
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val prefs: PreferenceProvider,
    private val api: ReviewerApi
) : ContentRepository {

    override fun getMovies(): Single<List<MovieDTO>> {
        return api.getMovies()
    }

    override fun performLogin(signInRequest: SignInRequest): Single<SignInResponse> {
        return api.performLogin(signInRequest)
    }

    override fun getToken(): String? {
        return prefs.getTokenFromPreferences()
    }

    override fun saveUserToken(token: String) {
        prefs.saveToken(token)
    }

}