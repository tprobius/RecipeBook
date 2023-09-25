package com.tprobius.recipebook.presentation.recipelist

import com.tprobius.recipebook.domain.entities.RecipeItem

sealed interface RecipeListState {
    object Initial : RecipeListState
    object Loading : RecipeListState
    data class Success(val recipeList: List<RecipeItem>) : RecipeListState
    object Error : RecipeListState
}