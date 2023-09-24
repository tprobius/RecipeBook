package com.tprobius.recipebook.di

import com.tprobius.recipebook.data.api.RecipeBookApi
import com.tprobius.recipebook.data.api.RecipeBookApi.Companion.BASE_URL
import com.tprobius.recipebook.data.repository.RecipeBookApiRepositoryImpl
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import com.tprobius.recipebook.domain.usecases.GetRecipeListUseCase
import com.tprobius.recipebook.presentation.recipedetails.RecipeDetailsViewModel
import com.tprobius.recipebook.presentation.recipelist.RecipeListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { provideRecipeBookApiRetrofit() }
    single { provideRecipeBookApi(get()) }
    single<RecipeBookApiRepository> { RecipeBookApiRepositoryImpl(get()) }
}

val databaseModule = module {
//    single { provideRecipeBookDatabase(get()) }
////    single<RecipeBookDatabaseRepository> { provideRecipeBookDatabaseRepository(get()) }
//    single<RecipeBookDatabaseRepository> { RecipeBookDatabaseRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { RecipeListViewModel(get()) }
    viewModel { RecipeDetailsViewModel() }
}

val useCasesModule = module {
    single { GetRecipeListUseCase(get()) }
}

private fun provideRecipeBookApiRetrofit(): Retrofit.Builder {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
    client.addInterceptor(logging)

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
}

private fun provideRecipeBookApi(retrofitBuilder: Retrofit.Builder): RecipeBookApi {
    return retrofitBuilder
        .build()
        .create(RecipeBookApi::class.java)
}


//fun provideRecipeBookDatabase(app: Application): RecipeBookDatabase {
//    return Room.databaseBuilder(
//        app,
//        RecipeBookDatabase::class.java,
//        RecipeBookDatabase.DATABASE_NAME
//    ).build()
//}
//
//fun provideRecipeBookDatabaseRepository(db: RecipeBookDatabase): RecipeBookDatabaseRepository {
//    return RecipeBookDatabaseRepositoryImpl(db.recipeBookDao)
//}