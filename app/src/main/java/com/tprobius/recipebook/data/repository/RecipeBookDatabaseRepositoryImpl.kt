package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.database.RecipeBookDao
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RecipeBookDatabaseRepositoryImpl(
    private val recipeBookDao: RecipeBookDao
) : RecipeBookDatabaseRepository {
    override suspend fun addNewRecipe(newRecipe: RecipeItem) {
        return withContext(Dispatchers.IO) { recipeBookDao.addNewRecipe(newRecipe) }
    }

    override suspend fun addRecipeList(recipeList: List<RecipeItem>) {
        return withContext(Dispatchers.IO) { recipeBookDao.addRecipeList(recipeList) }
    }

    override suspend fun getRecipeList(): Flow<List<RecipeItem>> {
        return withContext(Dispatchers.IO) { recipeBookDao.getRecipeList() }
    }

    override suspend fun deleteRecipeList() {
        return withContext(Dispatchers.IO) { recipeBookDao.deleteRecipeList() }
    }
}