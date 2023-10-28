package com.tprobius.recipebook.presentation.recipelist.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.fitCenter
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.tprobius.recipebook.R
import com.tprobius.recipebook.RecipeBookApp
import com.tprobius.recipebook.databinding.ItemRecipeListBinding
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.presentation.utils.FillSpace


class RecipeItemViewHolder(
    private var binding: ItemRecipeListBinding,
    private val onClickListener: (RecipeItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(recipeItem: RecipeItem) {
        with(binding) {
            nameTextView.text = recipeItem.name
            headlineTextView.text = recipeItem.headline
            timeTextView.text =
                RecipeBookApp.getResources().getString(R.string.time, recipeItem.time)
            caloriesTextView.text =
                if (recipeItem.calories.isNullOrEmpty()) {
                    RecipeBookApp.getResources().getString(R.string.no_data)
                } else {
                    recipeItem.calories
                }
            difficultyTextView.text = when (recipeItem.difficulty) {
                null -> RecipeBookApp.getResources().getString(R.string.no_data)
                0, 1 -> RecipeBookApp.getResources().getString(R.string.easy)
                2 -> RecipeBookApp.getResources().getString(R.string.medium)
                else -> RecipeBookApp.getResources().getString(R.string.hard)
            }

            cardView.setOnClickListener {
                onClickListener(recipeItem)
            }

            Glide.with(imageView.context)
                .load(recipeItem.image)
                .transform(MultiTransformation(FillSpace(), RoundedCorners(8)))
                .into(imageView)


//            Glide.with(imageView.context)
//                .asBitmap()
//                .load(recipeItem.image)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .transform(RoundedCorners(8))
//                .into(object : CustomTarget<Bitmap?>() {
//                    override fun onResourceReady(
//                        resource: Bitmap,
//                        transition: Transition<in Bitmap?>?
//                    ) {
//                        imageView.setImageBitmap(resource)
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {}
//                })
        }
    }
}