package com.tprobius.recipebook.data.entites

import com.google.gson.annotations.SerializedName
import com.tprobius.recipebook.presentation.recipelist.ListItem
import java.io.Serializable

data class RecipeListItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("headline")
    val headline: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("calories")
    val calories: String?,
    @SerializedName("proteins")
    val proteins: String?,
    @SerializedName("fats")
    val fats: String?,
    @SerializedName("carbos")
    val carbons: String?,
    @SerializedName("difficulty")
    val difficulty: Int?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("thumb")
    val thumb: String?
) : ListItem, Serializable