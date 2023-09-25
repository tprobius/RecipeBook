package com.tprobius.recipebook.presentation.recipeadding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.usecases.AddNewRecipeUseCase
import kotlinx.coroutines.launch

class RecipeAddingViewModel(
    private val addNewRecipeUseCase: AddNewRecipeUseCase
) : ViewModel() {
    private var _state: MutableLiveData<RecipeAddingState> = MutableLiveData()
    val state: LiveData<RecipeAddingState> = _state

    init {
        _state.value = RecipeAddingState.Initial
    }

    fun addNewRecipe(newRecipe: RecipeItem) {
        viewModelScope.launch {
            _state.value = RecipeAddingState.Loading
            try {
                addNewRecipeUseCase(newRecipe).let {
                    _state.postValue(RecipeAddingState.Success)
                }
            } catch (e: Exception) {
                _state.postValue(RecipeAddingState.Error)
            }
        }
    }
}