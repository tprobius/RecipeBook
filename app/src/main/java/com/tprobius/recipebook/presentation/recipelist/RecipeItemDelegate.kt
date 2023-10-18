package com.tprobius.recipebook.presentation.recipelist

import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.tprobius.recipebook.databinding.ItemRecipeListBinding
import com.tprobius.recipebook.domain.entities.ListItem
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.presentation.utils.FillSpace

fun recipeItemDelegate(onRecipeClick: (RecipeItem) -> Unit) =
    adapterDelegateViewBinding<RecipeItem, ListItem, ItemRecipeListBinding>(
        { layoutInflater, parent ->
            ItemRecipeListBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.recipeItem = item
            binding.imageView
            binding.cardView.setOnClickListener {
                onRecipeClick(item)
            }

            Glide.with(binding.imageView)
                .load(item.image)
                .transform(MultiTransformation(FillSpace(), RoundedCorners(8)))
                .into(binding.imageView)
        }
    }