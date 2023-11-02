package com.tprobius.recipebook.presentation.recipedetails

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tprobius.recipebook.R
import com.tprobius.recipebook.databinding.FragmentRecipeDetailsBinding
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.presentation.recipelist.RecipeListFragment
import com.tprobius.recipebook.presentation.utils.FillSpace
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailsFragment : Fragment() {
    private var _binding: FragmentRecipeDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: RecipeDetailsViewModel by viewModel()

    private lateinit var recipeItem: RecipeItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeItem = it.recipe as RecipeItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToRecipeListFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun navigateToRecipeListFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_main, RecipeListFragment())
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        viewModel.getRecipeDetails(recipeItem)
        setHandleState()
        setOnBackClick()
    }

    private fun handleState(state: RecipeDetailsState) {
        when (state) {
            RecipeDetailsState.Initial -> showInitialState()
            RecipeDetailsState.Loading -> showLoadingState()
            is RecipeDetailsState.Success -> showSuccessState()
            RecipeDetailsState.Error -> showErrorState()
        }
    }

    private fun setHandleState() {
        viewModel.state.observe(viewLifecycleOwner) {
            handleState(it)
        }
    }

    private fun setViewsVisibility(
        progressBarIsVisible: Boolean = false,
        errorImageViewIsVisible: Boolean = false,
        errorTextViewIsVisible: Boolean = false,
        recipeImageImageViewIsVisible: Boolean = false,
        recipeInfoCardViewIsVisible: Boolean = false,
        recipeNameTextViewIsVisible: Boolean = false,
        recipeHeadlineTextViewIsVisible: Boolean = false,
        descriptionScrollViewIsVisible: Boolean = false,
    ) {
        with(binding) {
            progressBar.isVisible = progressBarIsVisible
            errorImageView.isVisible = errorImageViewIsVisible
            errorTextView.isVisible = errorTextViewIsVisible
            recipeImageImageView.isVisible = recipeImageImageViewIsVisible
            recipeInfoCardView.isVisible = recipeInfoCardViewIsVisible
            recipeNameTextView.isVisible = recipeNameTextViewIsVisible
            recipeHeadlineTextView.isVisible = recipeHeadlineTextViewIsVisible
            descriptionScrollView.isVisible = descriptionScrollViewIsVisible
        }
    }

    private fun showInitialState() {
        setViewsVisibility()
    }

    private fun showLoadingState() {
        setViewsVisibility(progressBarIsVisible = true)
    }

    private fun showSuccessState() {
        setRecipeItem()
        setViewsVisibility(
            recipeImageImageViewIsVisible = true,
            recipeInfoCardViewIsVisible = true,
            recipeNameTextViewIsVisible = true,
            recipeHeadlineTextViewIsVisible = true,
            descriptionScrollViewIsVisible = true,
        )
    }

    private fun setRecipeItem() {
        binding.recipeNameTextView.text = recipeItem.name
        binding.recipeHeadlineTextView.text = recipeItem.headline
        binding.recipeDescriptionTextView.text = recipeItem.description
        binding.proteinTextView.text = getString(R.string.proteins, recipeItem.proteins)
        binding.fatsTextView.text = getString(R.string.fats, recipeItem.fats)
        binding.carbsTextView.text = getString(R.string.carbs, recipeItem.carbons)
        binding.timeChip.text = getString(R.string.time, recipeItem.time)
        binding.caloriesChip.text = recipeItem.calories
        binding.difficultyChip.text = when (recipeItem.difficulty) {
            0, 1 -> getString(R.string.easy)
            2 -> getString(R.string.medium)
            else -> getString(R.string.hard)
        }

        Glide.with(binding.recipeImageImageView)
            .load(recipeItem.image)
            .transform(MultiTransformation(FillSpace(), RoundedCorners(8)))
            .into(binding.recipeImageImageView)
    }

    private fun showErrorState() {
        setViewsVisibility(
            errorImageViewIsVisible = true,
            errorTextViewIsVisible = true
        )
    }

    private fun setOnBackClick() {
        binding.backImageView.setOnClickListener {
            navigateToRecipeListFragment()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val RECIPE_KEY = "recipe_key"

        private var Bundle.recipe
            get() =
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(
                        RECIPE_KEY,
                        RecipeItem::class.java
                    )

                    else -> @Suppress("DEPRECATION") getSerializable(RECIPE_KEY) as? RecipeItem
                }
            set(value) = putSerializable(RECIPE_KEY, value)

        fun newInstance(recipeItem: RecipeItem) = RecipeDetailsFragment().apply {
            arguments = Bundle().apply { this.recipe = recipeItem }
        }
    }
}