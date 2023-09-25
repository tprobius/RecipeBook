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

    private var recipeItem: RecipeItem? = null

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

    private fun showInitialState() {
        binding.progressBar.isVisible = false
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
        binding.recipeImageImageView.isVisible = false
        binding.recipeInfoCardView.isVisible = false
        binding.recipeNameTextView.isVisible = false
        binding.recipeHeadlineTextView.isVisible = false
        binding.descriptionScrollView.isVisible = false
    }

    private fun showLoadingState() {
        binding.progressBar.isVisible = true
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
        binding.recipeImageImageView.isVisible = false
        binding.recipeInfoCardView.isVisible = false
        binding.recipeNameTextView.isVisible = false
        binding.recipeHeadlineTextView.isVisible = false
        binding.descriptionScrollView.isVisible = false
    }

    private fun showSuccessState() {
        setRecipeItem()
        binding.progressBar.isVisible = false
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
        binding.recipeImageImageView.isVisible = true
        binding.recipeInfoCardView.isVisible = true
        binding.recipeNameTextView.isVisible = true
        binding.recipeHeadlineTextView.isVisible = true
        binding.descriptionScrollView.isVisible = true
    }

    private fun setRecipeItem() {
        binding.recipeItem = recipeItem
        Glide.with(binding.recipeImageImageView)
            .load(recipeItem?.image)
            .transform(MultiTransformation(FillSpace(), RoundedCorners(8)))
            .into(binding.recipeImageImageView)
    }

    private fun showErrorState() {
        binding.progressBar.isVisible = false
        binding.errorImageView.isVisible = true
        binding.errorTextView.isVisible = true
        binding.recipeImageImageView.isVisible = false
        binding.recipeInfoCardView.isVisible = false
        binding.recipeNameTextView.isVisible = false
        binding.recipeHeadlineTextView.isVisible = false
        binding.descriptionScrollView.isVisible = false
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