package com.tprobius.recipebook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tprobius.recipebook.domain.entities.RecipeItem

@Database(entities = [RecipeItem::class], version = 1)
abstract class RecipeBookDatabase : RoomDatabase() {
    abstract val recipeBookDao: RecipeBookDao

    companion object {
        const val DATABASE_NAME = "recipe_book_db"
    }
}