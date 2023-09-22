package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.entites.RecipeListItem
import retrofit2.Response

interface RecipeBookApiRepository {
    suspend fun getRecipeList(): Response<List<RecipeListItem>>
}