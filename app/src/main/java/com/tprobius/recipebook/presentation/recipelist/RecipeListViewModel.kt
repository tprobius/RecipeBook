package com.tprobius.recipebook.presentation.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.recipebook.domain.usecases.AddNewRecipeUseCase
import com.tprobius.recipebook.domain.usecases.GetRecipeListUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val getRecipeListUseCase: GetRecipeListUseCase,
    private val addNewRecipeUseCase: AddNewRecipeUseCase
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
                    _state.postValue(RecipeListState.Success(it.flattenToList()))
                }
            } catch (e: Exception) {
//                _state.postValue(RecipeListState.Error)
            }
        }
    }

    @OptIn(FlowPreview::class)
    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()
}

