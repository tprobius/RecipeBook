package com.tprobius.recipebook.domain.usecases

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository

class GetRecipeListUseCase(
    app: Application,
    private val apiRepository: RecipeBookApiRepository,
    private val databaseRepository: RecipeBookDatabaseRepository
) : RecipeBookUseCase {
    private val connectivityManager =
        app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    suspend operator fun invoke(): List<RecipeItem> {
        if (hasInternetConnection(connectivityManager)) {
            val recipeList = apiRepository.getRecipeList()
            if (recipeList.isNotEmpty()) {
                databaseRepository.addRecipeList(recipeList)
            }
        }

        return databaseRepository.getRecipeList()
    }
}