package com.tprobius.recipebook.presentation.recipedetails

import com.tprobius.recipebook.data.entites.RecipeListItem

sealed interface RecipeDetailsState {
    data object Initial : RecipeDetailsState
    data object Loading : RecipeDetailsState
    data object Success : RecipeDetailsState
    data object Error : RecipeDetailsState
}