package com.tprobius.recipebook.data.repository

import com.tprobius.recipebook.data.database.RecipeBookDao
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository
import kotlinx.coroutines.flow.Flow

class RecipeBookDatabaseRepositoryImpl(
    private val recipeBookDao: RecipeBookDao
) : RecipeBookDatabaseRepository {
//    override suspend fun addNewRecipe(newRecipe: RecipeItem) {
////        return recipeBookDao.addNewRecipe(newRecipe)
//    }

    override fun getRecipeList(): Flow<List<RecipeItem>> {
        return recipeBookDao.getRecipeList()
    }
}