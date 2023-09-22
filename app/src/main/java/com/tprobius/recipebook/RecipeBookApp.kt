package com.tprobius.recipebook

import android.app.Application
import com.tprobius.recipebook.di.netModule
import com.tprobius.recipebook.di.viewModelModule
import org.koin.core.context.startKoin

class RecipeBookApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                netModule,
                viewModelModule
            )
        }
    }
}