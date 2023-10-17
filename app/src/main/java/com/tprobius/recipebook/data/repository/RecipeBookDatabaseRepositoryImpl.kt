package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.database.RecipeBookDao
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RecipeBookDatabaseRepositoryImpl(
    private val recipeBookDao: RecipeBookDao,
    private val dispatcher: CoroutineDispatcher
) : RecipeBookDatabaseRepository {
    override suspend fun addNewRecipe(newRecipe: RecipeItem) {
        return withContext(dispatcher) { recipeBookDao.addNewRecipe(newRecipe) }
    }

    override suspend fun getRecipeList(): List<RecipeItem> {
        return withContext(dispatcher) { recipeBookDao.getRecipeList() }
    }

    override suspend fun deleteRecipeList() {
        return withContext(dispatcher) { recipeBookDao.deleteRecipeList() }
    }
}