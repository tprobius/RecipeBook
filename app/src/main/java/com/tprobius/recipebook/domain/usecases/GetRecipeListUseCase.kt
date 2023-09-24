package com.tprobius.recipebook.domain.usecases

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository
import com.tprobius.recipebook.utils.hasInternetConnection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

class GetRecipeListUseCase(
    app: Application,
    private val apiRepository: RecipeBookApiRepository,
    private val databaseRepository: RecipeBookDatabaseRepository
) {
    private val connectivityManager =
        app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default

    suspend operator fun invoke(): Flow<List<RecipeItem>> {
        return if (hasInternetConnection(connectivityManager)) {
            databaseRepository.deleteRecipeList()
            val result: Flow<List<RecipeItem>> =
                apiRepository.getRecipeList()
                    .onEach { news -> databaseRepository.addRecipeList(news) }
                    .flowOn(defaultDispatcher)
            result
        } else {
            val result: Flow<List<RecipeItem>> =
                databaseRepository.getRecipeList()
                    .flowOn(defaultDispatcher)
            result
        }
    }
}