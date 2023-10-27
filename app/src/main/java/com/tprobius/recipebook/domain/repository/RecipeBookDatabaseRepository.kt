package com.tprobius.recipebook.domain.repository

import com.tprobius.recipebook.domain.entities.RecipeItem

interface RecipeBookDatabaseRepository {
    suspend fun addNewRecipe(newRecipe: RecipeItem)

    suspend fun addRecipeList(recipeList: List<RecipeItem>)

    suspend fun getRecipeList(): List<RecipeItem>

    suspend fun deleteRecipeList()
}