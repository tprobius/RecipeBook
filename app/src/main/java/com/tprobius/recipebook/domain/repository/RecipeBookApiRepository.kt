package com.tprobius.recipebook.domain.repository

import com.tprobius.recipebook.domain.entities.RecipeItem

interface RecipeBookApiRepository {
    suspend fun getRecipeList(): List<RecipeItem>
}