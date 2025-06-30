package com.mvi.compose_animals_app.domain.model

data class AnimalDetails(
    val name: String,
    val taxonomy: Taxonomy?,
    val location: String,
    val speed: Speed?,
    val diet: String,
    val lifespan: String,
    val image: String
) {
    companion object {
        fun mock() = AnimalDetails(
            name = "Lion",
            taxonomy = Taxonomy.mock(),
            location = "Africa, India",
            speed = Speed.mock(),
            diet = "Carnivore",
            lifespan = "8 - 15 years",
            image = "lion.jpg"
        )
    }
}
