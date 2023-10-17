package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.api.RecipeBookApi
import com.tprobius.recipebook.data.mappers.toRecipeItem
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RecipeBookApiRepositoryImpl(
    private val recipeBookApi: RecipeBookApi,
    private val dispatcher: CoroutineDispatcher
) : RecipeBookApiRepository {
    override suspend fun getRecipeList(): List<RecipeItem> {
        return withContext(dispatcher) {
            recipeBookApi.getRecipeList().map {
                it.toRecipeItem()
            }
        }
    }
}