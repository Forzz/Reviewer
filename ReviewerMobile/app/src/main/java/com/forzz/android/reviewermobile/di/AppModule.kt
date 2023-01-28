package com.forzz.android.reviewermobile.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.forzz.android.reviewermobile.common.Constants
import com.forzz.android.reviewermobile.data.preferences.PreferenceProvider
import com.forzz.android.reviewermobile.data.remote.ReviewerApi
import com.forzz.android.reviewermobile.data.repository.ReviewRepositoryImpl
import com.forzz.android.reviewermobile.domain.repository.ContentRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideIsNetworkAvailable(@ApplicationContext context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ReviewerApi {
        return retrofit.create(ReviewerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(prefs: PreferenceProvider, api: ReviewerApi): ContentRepository {
        return ReviewRepositoryImpl(prefs, api)
    }

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context): PreferenceProvider {
        return PreferenceProvider(context)
    }
}