package com.mvi.compose_animals_app.presentation.features.animal_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mvi.compose_animals_app.core.ApiUtil
import com.mvi.compose_animals_app.core.extensions.shimmer
import com.mvi.compose_animals_app.domain.model.Animal
import com.mvi.compose_animals_app.presentation.components.SmartImage

@Composable
fun AnimalItem(
    animal: Animal,
    onClick: (Animal) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(animal) }
            .height(100.dp)
    ) {
        val url = ApiUtil.BASE_URL + animal.image

        SmartImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp)),
            url = url
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = animal.name,
                fontWeight = FontWeight.Bold
            )
            Text(text = animal.location)
        }
    }
}

// region Skeleton

@Composable
fun AnimalItemSkeleton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .shimmer()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmer()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmer()
            )
        }
    }
}

// endregion Skeleton

// region Previews

@Preview
@Composable
private fun Preview() {
    AnimalItem(
        animal = Animal.mock(),
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun AnimalItemSkeletonPreview() {
    AnimalItemSkeleton()
}

// endregion Previews
