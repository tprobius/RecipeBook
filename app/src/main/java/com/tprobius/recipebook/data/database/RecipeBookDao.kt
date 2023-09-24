package com.tprobius.recipebook.data.database

import androidx.room.Dao
import androidx.room.Query
import com.tprobius.recipebook.domain.entities.RecipeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeBookDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addNewRecipe(newRecipe: RecipeItem)

    @Query("SELECT * FROM recipes")
    fun getRecipeList(): Flow<List<RecipeItem>>
}