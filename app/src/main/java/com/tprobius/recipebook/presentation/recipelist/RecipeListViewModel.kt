package com.tprobius.recipebook.presentation.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.recipebook.data.entites.RecipeListItem
import com.tprobius.recipebook.data.repository.RecipeBookApiRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RecipeListViewModel(
    private val recipeBookApiRepository: RecipeBookApiRepository
) : ViewModel() {
    private var _state: MutableLiveData<RecipeListState> = MutableLiveData()
    val state: LiveData<RecipeListState> = _state

    init {
        _state.value = RecipeListState.Initial
    }

    fun getRecipeList() {
        viewModelScope.launch {
            lateinit var recipeList: Response<List<RecipeListItem>>

            _state.value = RecipeListState.Loading

            try {
                recipeList = recipeBookApiRepository.getRecipeList()
                if (recipeList.isSuccessful) {
                    _state.value = recipeList.body()?.let { RecipeListState.Success(it) }
                } else {
                    _state.value = RecipeListState.Error
                }
            } catch (e: Exception) {
                _state.value = RecipeListState.Error
            }
        }
    }
}