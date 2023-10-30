package com.tprobius.recipebook.presentation.recipelist

import androidx.recyclerview.widget.DiffUtil
import com.tprobius.recipebook.domain.entities.RecipeItem

open class RecipeItemDiffCallback : DiffUtil.ItemCallback<RecipeItem>() {
    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean =
        oldItem == newItem
}