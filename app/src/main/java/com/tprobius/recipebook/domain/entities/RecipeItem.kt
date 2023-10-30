package com.tprobius.recipebook.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recipes")
data class RecipeItem(
    @PrimaryKey(autoGenerate = true)
    val itemId: Long? = 0,
    val idRemote: String?,
    val name: String?,
    val headline: String?,
    val description: String?,
    val calories: String?,
    val proteins: String?,
    val fats: String?,
    val carbons: String?,
    val difficulty: Int?,
    val time: Int?,
    val image: String?
) : Serializable