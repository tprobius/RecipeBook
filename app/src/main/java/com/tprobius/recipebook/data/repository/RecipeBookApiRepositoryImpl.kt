package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.api.RecipeBookApi
import com.tprobius.recipebook.data.mappers.toRecipeItem
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RecipeBookApiRepositoryImpl(
    private val recipeBookApi: RecipeBookApi
) : RecipeBookApiRepository {
    override suspend fun getRecipeList(): Flow<List<RecipeItem>> {
        return withContext(Dispatchers.IO) {
            flow {
                emit(recipeBookApi.getRecipeList().map {
                    it.toRecipeItem()
                })
            }
        }
    }
}