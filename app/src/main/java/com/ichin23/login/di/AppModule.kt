package com.ichin23.login.di

import android.app.Application
import androidx.room.Room
import com.ichin23.login.data.UserDatabase
import com.ichin23.login.data.UserRepository
import com.ichin23.login.data.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideUserDatabase(app: Application):UserDatabase{
    return Room.databaseBuilder(
      app,
      UserDatabase::class.java,
      "user"
    ).build()
  }

  @Provides
  @Singleton
  fun provideUserRepository(db: UserDatabase):UserRepository{
    return UserRepositoryImpl(db.dao)
  }
}