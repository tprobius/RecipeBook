package com.tprobius.recipebook.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recipes")
data class RecipeItem(
    @PrimaryKey(autoGenerate = true)
    val idLocal: Long = -1,
    @ColumnInfo
    val idRemote: String?,
    @ColumnInfo
    val name: String?,
    @ColumnInfo
    val headline: String?,
    @ColumnInfo
    val description: String?,
    @ColumnInfo
    val calories: String?,
    @ColumnInfo
    val proteins: String?,
    @ColumnInfo
    val fats: String?,
    @ColumnInfo
    val carbons: String?,
    @ColumnInfo
    val difficulty: Int?,
    @ColumnInfo
    val time: String?,
    @ColumnInfo
    val image: String?
) : ListItem, Serializable