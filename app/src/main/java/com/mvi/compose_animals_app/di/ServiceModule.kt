package com.mvi.compose_animals_app.di

import com.mvi.compose_animals_app.data.remote.api.AnimalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideAnimalService(retrofit: Retrofit): AnimalService =
        retrofit.create(AnimalService::class.java)
}
