package com.tprobius.recipebook.domain.usecases

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository
import com.tprobius.recipebook.utils.hasInternetConnection
import kotlinx.coroutines.flow.Flow


class GetRecipeListUseCase(
    app: Application,
    private val apiRepository: RecipeBookApiRepository,
    private val databaseRepository: RecipeBookDatabaseRepository
) {
    private val connectivityManager =
        app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    operator fun invoke(): Flow<List<RecipeItem>> {
        return if (hasInternetConnection(connectivityManager)) {
            apiRepository.getRecipeList()
        } else {
            databaseRepository.getRecipeList()
        }
    }
}