package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.api.RecipeBookApi
import com.tprobius.recipebook.data.entites.RecipeListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RecipeBookApiRepositoryImpl(
    private val recipeBookApi: RecipeBookApi
) : RecipeBookApiRepository {
    override suspend fun getRecipeList(): Response<List<RecipeListItem>> {
        return withContext(Dispatchers.IO) { recipeBookApi.getRecipeList() }
    }
}