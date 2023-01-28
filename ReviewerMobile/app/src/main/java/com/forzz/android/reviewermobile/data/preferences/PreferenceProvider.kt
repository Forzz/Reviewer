package com.forzz.android.reviewermobile.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val TOKEN_VALUE = "TOKEN_VALUE"

class PreferenceProvider(
    context: Context
) {
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveToken(token: String) {
        preference.edit().putString(TOKEN_VALUE, token).apply()
    }

    fun getTokenFromPreferences(): String? {
        return preference.getString(TOKEN_VALUE, null)
    }
}