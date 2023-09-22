package com.tprobius.recipebook.presentation.recipelist

import com.tprobius.recipebook.data.entites.RecipeListItem

sealed interface RecipeListState {
    data object Initial : RecipeListState
    data object Loading : RecipeListState
    data class Success(val recipeList: List<RecipeListItem>) : RecipeListState
    data object Error : RecipeListState
}