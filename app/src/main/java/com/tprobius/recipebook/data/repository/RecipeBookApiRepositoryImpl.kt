package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.api.RecipeBookApi
import com.tprobius.recipebook.data.mappers.toRecipeItem
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeBookApiRepositoryImpl(
    private val recipeBookApi: RecipeBookApi
) : RecipeBookApiRepository {
    override suspend fun getRecipeList(): List<RecipeItem> {
        return withContext(Dispatchers.IO) {
            recipeBookApi.getRecipeList().map {
                it.toRecipeItem()
            }
        }
    }
}