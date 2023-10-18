package com.tprobius.recipebook.data.mappers

import com.tprobius.recipebook.data.model.RecipeListItem
import com.tprobius.recipebook.domain.entities.RecipeItem

fun RecipeListItem.toRecipeItem() = RecipeItem(
    idRemote = id,
    name = name,
    headline = headline,
    description = description,
    calories = calories,
    proteins = proteins,
    fats = fats,
    carbons = carbons,
    difficulty = difficulty,
    time = time?.filter { it.isDigit() }?.toInt(),
    image = image
)