package com.tprobius.recipebook.presentation.recipedetails

sealed interface RecipeDetailsState {
    object Initial : RecipeDetailsState
    object Loading : RecipeDetailsState
    object Success : RecipeDetailsState
    object Error : RecipeDetailsState
}