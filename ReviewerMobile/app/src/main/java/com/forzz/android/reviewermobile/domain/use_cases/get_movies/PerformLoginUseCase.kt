package com.forzz.android.reviewermobile.domain.use_cases.get_movies

import com.forzz.android.reviewermobile.data.remote.SignInRequest
import com.forzz.android.reviewermobile.data.remote.SignInResponse
import com.forzz.android.reviewermobile.data.remote.dto.MovieDTO
import com.forzz.android.reviewermobile.data.remote.dto.UserDTO
import com.forzz.android.reviewermobile.domain.model.User
import com.forzz.android.reviewermobile.domain.repository.ContentRepository
import com.forzz.android.reviewermobile.domain.use_cases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class PerformLoginUseCase @Inject constructor(
    private val repository: ContentRepository
) : SingleUseCase<SignInResponse>() {

    private lateinit var signInRequest: SignInRequest

    fun saveRequestData(login: String, password: String) {
        signInRequest = SignInRequest(login, password)
    }

    override fun buildUseCaseSingle(): Single<SignInResponse> {
        return repository.performLogin(signInRequest)
    }

    fun getToken(): String? {
        return repository.getToken()
    }

    fun saveTokenToPreferences(token: String) {
        repository.saveUserToken(token)
    }
}