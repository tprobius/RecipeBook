package com.tprobius.recipebook.data.api

import com.tprobius.recipebook.data.entites.RecipeListItem
import retrofit2.Response
import retrofit2.http.GET

interface RecipeBookApi {
    @GET("android-test/recipes.json")
    suspend fun getRecipeList(): Response<List<RecipeListItem>>

    companion object {
        const val BASE_URL =
            "https://hf-android-app.s3-eu-west-1.amazonaws.com/"
    }
}
