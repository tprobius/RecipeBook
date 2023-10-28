package com.tprobius.recipebook.presentation.recipelist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.tprobius.recipebook.domain.entities.RecipeItem

class RecipeItemDiffCallback : DiffUtil.ItemCallback<RecipeItem>() {
    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean =
        oldItem.idLocal == newItem.idLocal

    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean =
        oldItem == newItem
}