package com.tprobius.recipebook.domain.usecases

import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository

class AddNewRecipeUseCase(private val databaseRepository: RecipeBookDatabaseRepository) {
    suspend operator fun invoke(newRecipe: RecipeItem) = databaseRepository.addNewRecipe(newRecipe)
}