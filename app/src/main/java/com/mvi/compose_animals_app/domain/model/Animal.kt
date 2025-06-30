package com.mvi.compose_animals_app.domain.model

data class Animal(
    val name: String,
    val location: String,
    val image: String
) {
    companion object {
        fun mock() = Animal(
            name = "Lion",
            location = "Africa, India",
            image = "lion.jpg"
        )

        fun mockList() = listOf(
            Animal(
                name = "Lion",
                location = "Africa, India",
                image = "lion.jpg"
            ),
            Animal(
                name = "Gorilla",
                location = "Africa",
                image = "gorilla.jpg"
            ),
            Animal(
                name = "Elephant",
                location = "Africa, Middle East, South Asia",
                image = "elephant.jpg"
            )
        )
    }
}
