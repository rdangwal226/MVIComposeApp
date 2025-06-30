package com.mvi.compose_animals_app.data.mapper

import com.mvi.compose_animals_app.data.model.SpeedResponse
import com.mvi.compose_animals_app.domain.model.Speed

fun SpeedResponse.toSpeed() = Speed(
    metric = metric.orEmpty(),
    imperial = imperial.orEmpty()
)
