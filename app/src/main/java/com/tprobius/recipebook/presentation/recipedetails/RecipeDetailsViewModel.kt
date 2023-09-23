package com.tprobius.recipebook.presentation.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.recipebook.data.entites.RecipeListItem
import kotlinx.coroutines.launch

class RecipeDetailsViewModel : ViewModel() {
    private var _state: MutableLiveData<RecipeDetailsState> = MutableLiveData()
    val state: LiveData<RecipeDetailsState> = _state

    init {
        _state.value = RecipeDetailsState.Initial
    }

    fun getRecipeDetails(recipeItem: RecipeListItem?) {
        viewModelScope.launch {
            _state.value = RecipeDetailsState.Loading
            if (recipeItem != null) {
                _state.value = RecipeDetailsState.Success
            } else {
                _state.value = RecipeDetailsState.Error

            }
        }
    }
}