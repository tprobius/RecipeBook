package com.tprobius.recipebook.presentation.recipeadding

sealed interface RecipeAddingState {
    object Initial : RecipeAddingState
    object Loading : RecipeAddingState
    object Success : RecipeAddingState
    object Error : RecipeAddingState
}