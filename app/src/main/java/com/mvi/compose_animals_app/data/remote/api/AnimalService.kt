package com.mvi.compose_animals_app.data.remote.api

import com.mvi.compose_animals_app.data.model.AnimalResponse
import retrofit2.http.GET

interface AnimalService {
    @GET("animals.json")
    suspend fun getAnimals(): List<AnimalResponse>
}
