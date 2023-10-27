package com.tprobius.recipebook.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tprobius.recipebook.domain.entities.RecipeItem

@Dao
interface RecipeBookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewRecipe(newRecipe: RecipeItem)

    @Transaction
    fun addRecipeList(recipeList: List<RecipeItem>) {
        for (recipe in recipeList) {
            addNewRecipe(recipe)
        }
    }

    @Query("SELECT * FROM recipes")
    fun getRecipeList(): List<RecipeItem>

    @Query("DELETE FROM recipes")
    fun deleteRecipeList()
}