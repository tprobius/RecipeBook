package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.api.RecipeBookApi
import com.tprobius.recipebook.data.model.RecipeListItem
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class RecipeBookApiRepositoryImpl(
    private val recipeBookApi: RecipeBookApi,
    private val dispatcher: CoroutineDispatcher
) : RecipeBookApiRepository {
    override suspend fun getRecipeList(): List<RecipeItem> {
        lateinit var serverResponse: Response<List<RecipeListItem>>
        val resultList: MutableList<RecipeItem> = mutableListOf()
        withContext(dispatcher) {
            serverResponse = recipeBookApi.getRecipeList()
            if (serverResponse.isSuccessful) {
                serverResponse.body()?.map {
                    resultList.add(it.toRecipeItem())
                }
            }
        }
        return resultList
    }
}