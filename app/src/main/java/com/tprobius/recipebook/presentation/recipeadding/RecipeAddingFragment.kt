package com.tprobius.recipebook.presentation.recipeadding

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.tprobius.recipebook.R
import com.tprobius.recipebook.databinding.FragmentRecipeAddingBinding
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.presentation.recipelist.RecipeListFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeAddingFragment : Fragment() {
    private var _binding: FragmentRecipeAddingBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: RecipeAddingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeAddingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        setFieldsChecker()
        setOnBackClick()
        setOnSaveClick()
    }

    private fun handleState(state: RecipeAddingState) {
        when (state) {
            RecipeAddingState.Initial -> showInitialState()
            RecipeAddingState.Loading -> showLoadingState()
            RecipeAddingState.Success -> showSuccessState()
            RecipeAddingState.Error -> showErrorState()
        }
    }

    private fun setOnBackClick() {
        binding.backImageView.setOnClickListener {
            navigateToRecipeListFragment()
        }
    }

    private fun navigateToRecipeListFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_main, RecipeListFragment())
            .commit()
    }

    private fun setViewsVisibility(
        progressBarIsVisible: Boolean = false,
        errorImageViewIsVisible: Boolean = false,
        errorTextViewIsVisible: Boolean = false,
        successImageViewVIsVisible: Boolean = false,
        recipeImageImageViewIsVisible: Boolean = false,
        recipeDetailsCardViewIsVisible: Boolean = false,
        recipeNameEditTextIsVisible: Boolean = false,
        recipeHeadlineEditTextIsVisible: Boolean = false,
        recipeDescriptionEditTextVisibility: Boolean = false,
        recipeCaloriesEditTextIsVisible: Boolean = false,
        recipeProteinsEditTextIsVisible: Boolean = false,
        recipeFatsEditTextIsVisible: Boolean = false,
        recipeCarbsEditTextIsVisible: Boolean = false,
        recipeTimeEditTextIsVisible: Boolean = false,
        recipeDifficultyEditTextIsVisible: Boolean = false,
        saveButtonIsVisible: Boolean = false,
        saveButtonIsEnabled: Boolean = false
    ) {
        with(binding) {
            progressBar.isVisible = progressBarIsVisible
            errorImageView.isVisible = errorImageViewIsVisible
            errorTextView.isVisible = errorTextViewIsVisible
            successImageView.isVisible = successImageViewVIsVisible
            recipeImageImageView.isVisible = recipeImageImageViewIsVisible
            recipeDetailsCardView.isVisible = recipeDetailsCardViewIsVisible
            recipeNameEditText.isVisible = recipeNameEditTextIsVisible
            recipeHeadlineEditText.isVisible = recipeHeadlineEditTextIsVisible
            recipeDescriptionEditText.isVisible = recipeDescriptionEditTextVisibility
            recipeCaloriesEditText.isVisible = recipeCaloriesEditTextIsVisible
            recipeProteinsEditText.isVisible = recipeProteinsEditTextIsVisible
            recipeFatsEditText.isVisible = recipeFatsEditTextIsVisible
            recipeCarbsEditText.isVisible = recipeCarbsEditTextIsVisible
            recipeTimeEditText.isVisible = recipeTimeEditTextIsVisible
            recipeDifficultyEditText.isVisible = recipeDifficultyEditTextIsVisible
            saveButton.isVisible = saveButtonIsVisible
            saveButton.isEnabled = saveButtonIsEnabled
        }
    }

    private fun showInitialState() {
        setViewsVisibility(
            recipeImageImageViewIsVisible = true,
            recipeDetailsCardViewIsVisible = true,
            recipeNameEditTextIsVisible = true,
            recipeHeadlineEditTextIsVisible = true,
            recipeDescriptionEditTextVisibility = true,
            recipeCaloriesEditTextIsVisible = true,
            recipeProteinsEditTextIsVisible = true,
            recipeFatsEditTextIsVisible = true,
            recipeCarbsEditTextIsVisible = true,
            recipeTimeEditTextIsVisible = true,
            recipeDifficultyEditTextIsVisible = true,
            saveButtonIsVisible = true
        )
    }

    private fun setFieldsChecker() {
        binding.recipeNameEditText.setImeAction(binding.saveButton)
        binding.recipeHeadlineEditText.setImeAction(binding.saveButton)
        binding.recipeDescriptionEditText.setImeAction(binding.saveButton)
        binding.recipeCaloriesEditText.setImeAction(binding.saveButton)
        binding.recipeProteinsEditText.setImeAction(binding.saveButton)
        binding.recipeFatsEditText.setImeAction(binding.saveButton)
        binding.recipeCarbsEditText.setImeAction(binding.saveButton)
        binding.recipeTimeEditText.setImeAction(binding.saveButton)
        binding.recipeDifficultyEditText.setImeAction(binding.saveButton)
    }

    private fun TextInputEditText.setImeAction(button: Button) {
        setOnEditorActionListener { _, _, _ ->
            if (checkFieldsEmptiness()) {
                button.isEnabled = false
                false
            } else {
                button.isEnabled = true
                false
            }
        }
    }

    private fun checkFieldsEmptiness(): Boolean {
        return with(binding) {
            recipeNameEditText.text.isNullOrEmpty() ||
                    recipeHeadlineEditText.text.isNullOrEmpty() ||
                    recipeDescriptionEditText.text.isNullOrEmpty() ||
                    recipeCaloriesEditText.text.isNullOrEmpty() ||
                    recipeProteinsEditText.text.isNullOrEmpty() ||
                    recipeFatsEditText.text.isNullOrEmpty() ||
                    recipeCarbsEditText.text.isNullOrEmpty() ||
                    recipeTimeEditText.text.isNullOrEmpty() ||
                    recipeDifficultyEditText.text.isNullOrEmpty()
        }
    }

    private fun setOnSaveClick() {
        binding.saveButton.setOnClickListener {
            viewModel.addNewRecipe(
                RecipeItem(
                    idRemote = "",
                    name = binding.recipeNameEditText.text.toString(),
                    headline = binding.recipeHeadlineEditText.text.toString(),
                    description = binding.recipeDescriptionEditText.text.toString(),
                    calories = binding.recipeCaloriesEditText.text.toString() + " kcal",
                    proteins = binding.recipeProteinsEditText.text.toString(),
                    fats = binding.recipeFatsEditText.text.toString(),
                    carbons = binding.recipeCarbsEditText.text.toString(),
                    difficulty = binding.recipeDifficultyEditText.text.toString().toInt(),
                    time = binding.recipeTimeEditText.text.toString().toInt(),
                    image = null
                )
            )
        }
    }

    private fun showLoadingState() {
        setViewsVisibility(progressBarIsVisible = true)
    }

    private fun showSuccessState() {
        setViewsVisibility(
            progressBarIsVisible = true,
            successImageViewVIsVisible = true
        )
        binding.successImageView.alpha = 0f

        AnimatorSet().apply {
            playSequentially(
                ObjectAnimator.ofFloat(binding.progressBar, View.ALPHA, 0F),
                ObjectAnimator.ofFloat(binding.successImageView, View.ALPHA, 1F)
            )
            duration = 500
            start()
        }

        lifecycleScope.launch {
            delay(1500)
            navigateToRecipeList()
        }
    }

    private fun navigateToRecipeList() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_main, RecipeListFragment())
            .commit()
    }

    private fun showErrorState() {
        setViewsVisibility(
            errorImageViewIsVisible = true,
            errorTextViewIsVisible = true
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}