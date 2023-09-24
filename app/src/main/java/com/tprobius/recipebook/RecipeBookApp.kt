package com.tprobius.recipebook

import android.app.Application
import com.tprobius.recipebook.di.apiModule
import com.tprobius.recipebook.di.databaseModule
import com.tprobius.recipebook.di.useCasesModule
import com.tprobius.recipebook.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RecipeBookApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RecipeBookApp)
            modules(
                apiModule,
                databaseModule,
                viewModelModule,
                useCasesModule
            )
        }
    }
}