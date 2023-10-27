package com.tprobius.recipebook.presentation.recipelist

import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.tprobius.recipebook.R
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
            with(binding) {
                nameTextView.text = item.name
                headlineTextView.text = item.headline
                timeTextView.text = item.time?.let { time -> getString(R.string.time, time) }
                caloriesTextView.text =
                    if (item.calories.isNullOrEmpty()) {
                        getString(R.string.no_data)
                    } else {
                        item.calories
                    }
                difficultyTextView.text = when (item.difficulty) {
                    null -> getString(R.string.no_data)
                    0, 1 -> getString(R.string.easy)
                    2 -> getString(R.string.medium)
                    else -> getString(R.string.hard)
                }

                imageView
                cardView.setOnClickListener {
                    onRecipeClick(item)
                }

                Glide.with(imageView)
                    .load(item.image)
                    .transform(MultiTransformation(FillSpace(), RoundedCorners(8)))
                    .into(imageView)
            }
        }
    }