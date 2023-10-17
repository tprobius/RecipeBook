package com.tprobius.recipebook.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tprobius.recipebook.domain.entities.RecipeItem

@Dao
interface RecipeBookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewRecipe(newRecipe: RecipeItem)

    @Query("SELECT * FROM recipes")
    fun getRecipeList(): List<RecipeItem>

    @Query("DELETE FROM recipes")
    fun deleteRecipeList()
}