package com.gyurim.movieapp.di

import com.gyurim.movieapp.data.remote.NaverMovieApi
import com.gyurim.movieapp.util.UserInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
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
                .addHeader("X-Naver-Client-Id", UserInfo.NAVER_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", UserInfo.NAVER_CLIENT_SECRET)
                .build()
            it.proceed(newRequest)
        }
    }
}