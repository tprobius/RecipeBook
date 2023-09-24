package com.tprobius.recipebook.domain.repository

import com.tprobius.recipebook.domain.entities.RecipeItem
import kotlinx.coroutines.flow.Flow

interface RecipeBookDatabaseRepository {
//    suspend fun addNewRecipe(newRecipe: RecipeItem)

    fun getRecipeList(): Flow<List<RecipeItem>>
}