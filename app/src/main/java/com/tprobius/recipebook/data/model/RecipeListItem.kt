package com.tprobius.recipebook.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.recipebook.domain.entities.RecipeItem

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
) {
    fun toRecipeItem() = RecipeItem(
        itemId = id.hashCode().toLong(),
        idRemote = id,
        name = name,
        headline = headline,
        description = description,
        calories = calories,
        proteins = proteins,
        fats = fats,
        carbons = carbons,
        difficulty = difficulty,
        time = time?.filter { it.isDigit() }?.toInt(),
        image = image
    )
}