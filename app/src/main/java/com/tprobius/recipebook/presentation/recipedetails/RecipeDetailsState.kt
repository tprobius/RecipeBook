package com.tprobius.recipebook.presentation.recipedetails

sealed interface RecipeDetailsState {
    data object Initial : RecipeDetailsState
    data object Loading : RecipeDetailsState
    data object Success : RecipeDetailsState
    data object Error : RecipeDetailsState
}