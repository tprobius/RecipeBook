package com.tprobius.recipebook.presentation.recipelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tprobius.recipebook.databinding.ItemRecipeListBinding
import com.tprobius.recipebook.domain.entities.RecipeItem

class RecipeListAdapter(
    private val onClickListener: (RecipeItem) -> Unit
) : ListAdapter<RecipeItem, RecipeItemViewHolder>(RecipeItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder =
        RecipeItemViewHolder(
            ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context)),
            onClickListener
        )

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) =
        holder.bind(getItem(position))
}