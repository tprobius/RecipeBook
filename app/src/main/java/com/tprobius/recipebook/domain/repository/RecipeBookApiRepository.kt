package com.tprobius.recipebook.domain.repository

import com.tprobius.recipebook.domain.entities.RecipeItem
import kotlinx.coroutines.flow.Flow

interface RecipeBookApiRepository {
    fun getRecipeList(): Flow<List<RecipeItem>>
}