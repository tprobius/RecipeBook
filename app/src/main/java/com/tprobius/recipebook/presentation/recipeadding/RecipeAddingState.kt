package com.tprobius.recipebook.presentation.recipeadding

sealed interface RecipeAddingState {
    data object Initial : RecipeAddingState
    data object Loading : RecipeAddingState
    data object Success : RecipeAddingState
    data object Error : RecipeAddingState
}