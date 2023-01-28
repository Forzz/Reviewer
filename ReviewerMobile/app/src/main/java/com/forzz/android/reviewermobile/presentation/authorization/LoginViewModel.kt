package com.forzz.android.reviewermobile.presentation.authorization

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.forzz.android.reviewermobile.domain.model.User
import com.forzz.android.reviewermobile.domain.use_cases.get_movies.PerformLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val performLoginUseCase: PerformLoginUseCase,
) : ViewModel() {

    val userData = MutableLiveData<User>()
    val isLoad = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    val tokenFromResponse = MutableLiveData<String>()

    init {
        isLoad.value = false
        isError.value = false
    }

    fun getTokenFromPreferences(): String? = performLoginUseCase.getToken()

    fun isTokenExists(): Boolean = performLoginUseCase.getToken() != null

    fun saveData(login: String, password: String) =
        performLoginUseCase.saveRequestData(login, password)

    fun isErrorOccur() = isError.value

    fun performLogin(login: String, password: String) {
        performLoginUseCase.execute(
            onSuccess = {
                isLoad.value = true
                isError.value = false
                tokenFromResponse.value = it.token
                Log.d("AUTH", "SUCCESS: ${it.token}")
            },
            onError = {
                isError.value = true
                Log.d("AUTH", "ERROR")
            }
        )
    }

    fun saveTokenToPreferences(token: String) {
        performLoginUseCase.saveTokenToPreferences(token)
    }

}