package com.tprobius.recipebook.data.mappers

import com.tprobius.recipebook.data.model.RecipeListItem
import com.tprobius.recipebook.domain.entities.RecipeItem

fun RecipeListItem.toRecipeItem() = RecipeItem(
    idLocal = 0,
    idRemote = id,
    name = name,
    headline = headline,
    description = description,
    calories = calories,
    proteins = proteins,
    fats = fats,
    carbons = carbons,
    difficulty = difficulty,
    time = time,
    image = image
)

fun RecipeItem.toRecipeListItem() = RecipeListItem(
    id = idLocal.toString(),
    name = name,
    headline = headline,
    description = description,
    calories = calories,
    proteins = proteins,
    fats = fats,
    carbons = carbons,
    difficulty = difficulty,
    time = time,
    country = null,
    image = image,
    thumb = null
)