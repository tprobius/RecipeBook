package com.tprobius.recipebook.di

import android.app.Application
import androidx.room.Room
import com.tprobius.recipebook.data.api.RecipeBookApi
import com.tprobius.recipebook.data.api.RecipeBookApi.Companion.BASE_URL
import com.tprobius.recipebook.data.database.RecipeBookDatabase
import com.tprobius.recipebook.data.repository.RecipeBookApiRepositoryImpl
import com.tprobius.recipebook.data.repository.RecipeBookDatabaseRepositoryImpl
import com.tprobius.recipebook.domain.repository.RecipeBookApiRepository
import com.tprobius.recipebook.domain.repository.RecipeBookDatabaseRepository
import com.tprobius.recipebook.domain.usecases.AddNewRecipeUseCase
import com.tprobius.recipebook.domain.usecases.GetRecipeListUseCase
import com.tprobius.recipebook.presentation.recipeadding.RecipeAddingViewModel
import com.tprobius.recipebook.presentation.recipedetails.RecipeDetailsViewModel
import com.tprobius.recipebook.presentation.recipelist.RecipeListViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { provideRecipeBookApiRetrofit() }
    single { provideRecipeBookApi(retrofitBuilder = get()) }
    factory<RecipeBookApiRepository> {
        RecipeBookApiRepositoryImpl(
            recipeBookApi = get(),
            dispatcher = Dispatchers.IO
        )
    }
}

val databaseModule = module {
    single { provideRecipeBookDatabase(app = get()).recipeBookDao }
    single<RecipeBookDatabaseRepository> {
        RecipeBookDatabaseRepositoryImpl(
            recipeBookDao = get(),
            dispatcher = Dispatchers.IO
        )
    }
}

val useCasesModule = module {
    single { GetRecipeListUseCase(app = get(), apiRepository = get(), databaseRepository = get()) }
    single { AddNewRecipeUseCase(databaseRepository = get()) }
}

val viewModelModule = module {
    viewModel { RecipeListViewModel(getRecipeListUseCase = get()) }
    viewModel { RecipeDetailsViewModel() }
    viewModel { RecipeAddingViewModel(addNewRecipeUseCase = get()) }
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

fun provideRecipeBookDatabase(app: Application): RecipeBookDatabase {
    return Room.databaseBuilder(
        app,
        RecipeBookDatabase::class.java,
        RecipeBookDatabase.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}