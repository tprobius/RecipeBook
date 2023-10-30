package com.tprobius.recipebook.presentation.recipelist

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.tprobius.recipebook.domain.entities.RecipeItem

class RecipeItemAdapter(onRecipeClick: (RecipeItem) -> Unit) :
    AsyncListDifferDelegationAdapter<RecipeItem>(RecipeItemDiffCallback()) {
    init {
        delegatesManager.addDelegate(
            RecipeItemDelegate.recipeItemDelegate(onRecipeClick)
        ).addDelegate(RecipeItemDelegate.recipeItemDelegate(onRecipeClick))
    }
}