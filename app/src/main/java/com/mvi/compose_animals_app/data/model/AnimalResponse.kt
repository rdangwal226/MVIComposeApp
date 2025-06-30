package com.mvi.compose_animals_app.data.model

data class AnimalResponse(
    val name: String?,
    val taxonomy: TaxonomyResponse?,
    val location: String?,
    val speed: SpeedResponse?,
    val diet: String?,
    val lifespan: String?,
    val image: String?
)
