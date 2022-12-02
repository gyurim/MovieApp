package com.gyurim.movieapp.di

import android.content.Context
import androidx.room.Room
import com.gyurim.movieapp.data.local.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // 모듈로 지정된 클래스는 hilt에게 인스턴스를 제공하는 방법을 알려줌
@InstallIn(SingletonComponent::class) // 각 모듈이 어떤 Scope 에서 사용되는지 InstallIn 으로 알려줘야함
object DatabaseModule {
    @Provides // 외부 라이브러리에서 제공되거나 builder 패턴으로 제공되는 경우 @Provides를 통해 인스턴스를 생성하는 방법을 알려줌
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "MovieDatabase"
        ).build()
    }
}