package com.tprobius.recipebook.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tprobius.recipebook.domain.entities.RecipeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeBookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNewRecipe(newRecipe: RecipeItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipeList(recipeList: List<RecipeItem>)

    @Query("SELECT * FROM recipes")
    fun getRecipeList(): Flow<List<RecipeItem>>

    @Query("DELETE FROM recipes")
    fun deleteRecipeList()
}