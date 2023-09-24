package com.tprobius.recipebook.domain.repository

import com.tprobius.recipebook.domain.entities.RecipeItem
import kotlinx.coroutines.flow.Flow

interface RecipeBookDatabaseRepository {
    suspend fun addNewRecipe(newRecipe: RecipeItem)

    suspend fun addRecipeList(recipeList: List<RecipeItem>)

    suspend fun getRecipeList(): Flow<List<RecipeItem>>

    suspend fun deleteRecipeList()
}