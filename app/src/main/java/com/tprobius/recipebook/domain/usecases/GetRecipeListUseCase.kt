package com.tprobius.recipebook.domain.usecases

import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
//import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetRecipeListUseCase(
    private val apiRepository: RecipeBookApiRepository,
//    private val databaseRepository: RecipeBookDatabaseRepository
) {
    operator fun invoke(): Flow<List<RecipeItem>> {
//        return try {
        return apiRepository.getRecipeList()
//        } catch (e: Exception) {
//            databaseRepository.getRecipeList()
//        }
    }
}