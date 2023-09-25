package com.tprobius.recipebook.presentation.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.recipebook.domain.usecases.GetRecipeListUseCase
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val getRecipeListUseCase: GetRecipeListUseCase
) : ViewModel() {
    private var _state: MutableLiveData<RecipeListState> = MutableLiveData()
    val state: LiveData<RecipeListState> = _state

    init {
        _state.value = RecipeListState.Initial
    }

    fun getRecipeList() {
        viewModelScope.launch {
            _state.value = RecipeListState.Loading
            try {
                getRecipeListUseCase().let {
                    if (it.isEmpty()) {
                        _state.postValue(RecipeListState.Error)

                    } else {
                        _state.postValue(RecipeListState.Success(it))
                    }
                }
            } catch (e: Exception) {
                _state.postValue(RecipeListState.Error)
            }
        }
    }
}