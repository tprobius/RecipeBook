package com.tprobius.recipebook.presentation.recipelist

import androidx.recyclerview.widget.DiffUtil
import com.tprobius.recipebook.domain.entities.ListItem

open class RecipeItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.equals(newItem)
}