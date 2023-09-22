package com.tprobius.recipebook.presentation.recipelist

import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.tprobius.recipebook.data.entites.RecipeListItem
import com.tprobius.recipebook.databinding.ItemRecipeListBinding
import java.nio.charset.Charset
import java.security.MessageDigest

fun recipeItemDelegate() =
    adapterDelegateViewBinding<RecipeListItem, ListItem, ItemRecipeListBinding>(
        { layoutInflater, parent ->
            ItemRecipeListBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.recipeListItem = item
            binding.imageImageView

            Glide.with(binding.imageImageView)
                .load(item.thumb)
                .transform(MultiTransformation(FillSpace(), RoundedCorners(8)))
                .into(binding.imageImageView)

        }
    }

class FillSpace : BitmapTransformation() {
    public override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return if (toTransform.width == outWidth && toTransform.height == outHeight) {
            toTransform
        } else Bitmap.createScaledBitmap(toTransform, outWidth, outHeight, true)
    }

    override fun equals(other: Any?): Boolean {
        return other is FillSpace
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }

    companion object {
        private const val ID = ""
        private val ID_BYTES = ID.toByteArray(Charset.forName("UTF-8"))
    }
}