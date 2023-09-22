package com.tprobius.recipebook.presentation.recipelist

import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.tprobius.recipebook.data.entites.RecipeListItem
import com.tprobius.recipebook.databinding.ItemRecipeListBinding
import com.tprobius.recipebook.utils.FillSpace

fun recipeItemDelegate(onRecipeClick: (RecipeListItem) -> Unit) =
    adapterDelegateViewBinding<RecipeListItem, ListItem, ItemRecipeListBinding>(
        { layoutInflater, parent ->
            ItemRecipeListBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.recipeListItem = item
            binding.imageImageView
            binding.recipeListItemCardView.setOnClickListener {
                onRecipeClick(item)
            }

            Glide.with(binding.imageImageView)
                .load(item.thumb)
                .transform(MultiTransformation(FillSpace(), RoundedCorners(8)))
                .into(binding.imageImageView)
        }
    }