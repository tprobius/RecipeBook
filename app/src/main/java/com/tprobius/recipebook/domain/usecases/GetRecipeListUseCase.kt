package com.tprobius.recipebook.domain.usecases

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.tprobius.recipebook.MainActivity
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository
import kotlin.properties.Delegates

class GetRecipeListUseCase(
    app: Application,
    private val apiRepository: RecipeBookApiRepository,
    private val databaseRepository: RecipeBookDatabaseRepository
) : RecipeBookUseCase {
    private val connectivityManager =
        app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var getDataFromServer by Delegates.notNull<Boolean>()

    suspend operator fun invoke(): List<RecipeItem> {
        if (MainActivity.getSessionState()) {
            getDataFromServer = hasInternetConnection(connectivityManager)
            if (getDataFromServer) {
                val recipeList = apiRepository.getRecipeList()
                if (recipeList.isNotEmpty()) {
                    databaseRepository.addRecipeList(recipeList)
                }
            }
            MainActivity.setSessionState()
        }

        return databaseRepository.getRecipeList()
    }
}