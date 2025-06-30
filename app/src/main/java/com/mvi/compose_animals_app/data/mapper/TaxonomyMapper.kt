package com.mvi.compose_animals_app.data.mapper

import com.mvi.compose_animals_app.data.model.TaxonomyResponse
import com.mvi.compose_animals_app.domain.model.Taxonomy

fun TaxonomyResponse.toTaxonomy() = Taxonomy(
    kingdom = kingdom.orEmpty(),
    order = kingdom.orEmpty(),
    family = kingdom.orEmpty(),
)
