package com.gyurim.movieapp.presentation.di

import com.gyurim.movieapp.presentation.data.remote.NaverMovieApi
import com.gyurim.movieapp.presentation.util.UserInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val naver_movie_url = "https://openapi.naver.com/v1/"

    @Singleton
    @Provides
    fun provideHttpClient(headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideNaverRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(naver_movie_url)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideNaverMovieApi(retrofit: Retrofit): NaverMovieApi {
        return retrofit.create(NaverMovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val newRequest = it.request().newBuilder()
                .addHeader("naver_client_id", UserInfo.NAVER_CLIENT_ID)
                .addHeader("naver_client_secret", UserInfo.NAVER_CLIENT_SECRET)
                .build()
            it.proceed(newRequest)
        }
    }
}