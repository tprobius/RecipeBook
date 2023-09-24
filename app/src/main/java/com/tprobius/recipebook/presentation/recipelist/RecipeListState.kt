package com.tprobius.recipebook.presentation.recipelist

import com.tprobius.recipebook.domain.entities.RecipeItem

sealed interface RecipeListState {
    data object Initial : RecipeListState
    data object Loading : RecipeListState
    data class Success(val recipeList: List<RecipeItem>) : RecipeListState
    data object Error : RecipeListState
}