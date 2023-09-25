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
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: RecipeAddingViewModel by viewModel()
    private val recipeId: Long = 0

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

    private fun showInitialState() {
        binding.progressBar.isVisible = false
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
        binding.successImageView.isVisible = false
        binding.recipeImageImageView.isVisible = true
        binding.recipeDetailsCardView.isVisible = true
        binding.recipeNameEditText.isVisible = true
        binding.recipeHeadlineEditText.isVisible = true
        binding.recipeDescriptionEditText.isVisible = true
        binding.recipeCaloriesEditText.isVisible = true
        binding.recipeProteinsEditText.isVisible = true
        binding.recipeFatsEditText.isVisible = true
        binding.recipeCarbsEditText.isVisible = true
        binding.recipeTimeEditText.isVisible = true
        binding.recipeDifficultyEditText.isVisible = true
        binding.saveButton.isVisible = true
        binding.saveButton.isEnabled = false
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
                    idLocal = recipeId,
                    idRemote = recipeId.toString(),
                    name = binding.recipeNameEditText.text.toString(),
                    headline = binding.recipeHeadlineEditText.text.toString(),
                    description = binding.recipeDescriptionEditText.text.toString(),
                    calories = binding.recipeCaloriesEditText.text.toString() + " kcal",
                    proteins = binding.recipeProteinsEditText.text.toString(),
                    fats = binding.recipeFatsEditText.text.toString(),
                    carbons = binding.recipeCarbsEditText.text.toString(),
                    difficulty = binding.recipeDifficultyEditText.text.toString().toInt(),
                    time = "PT" + binding.recipeTimeEditText.text.toString() + "M",
                    image = null
                )
            )
        }
    }

    private fun showLoadingState() {
        binding.progressBar.isVisible = true
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
        binding.successImageView.isVisible = false
        binding.recipeImageImageView.isVisible = false
        binding.recipeDetailsCardView.isVisible = false
        binding.recipeNameEditText.isVisible = false
        binding.recipeHeadlineEditText.isVisible = false
        binding.recipeDescriptionEditText.isVisible = false
        binding.recipeCaloriesEditText.isVisible = false
        binding.recipeProteinsEditText.isVisible = false
        binding.recipeFatsEditText.isVisible = false
        binding.recipeCarbsEditText.isVisible = false
        binding.recipeTimeEditText.isVisible = false
        binding.recipeDifficultyEditText.isVisible = false
        binding.saveButton.isVisible = false
        binding.saveButton.isEnabled = false
    }

    private fun showSuccessState() {
        binding.progressBar.isVisible = true
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
        binding.successImageView.isVisible = true
        binding.successImageView.alpha = 0f
        binding.recipeImageImageView.isVisible = false
        binding.recipeDetailsCardView.isVisible = false
        binding.recipeNameEditText.isVisible = false
        binding.recipeHeadlineEditText.isVisible = false
        binding.recipeDescriptionEditText.isVisible = false
        binding.recipeCaloriesEditText.isVisible = false
        binding.recipeProteinsEditText.isVisible = false
        binding.recipeFatsEditText.isVisible = false
        binding.recipeCarbsEditText.isVisible = false
        binding.recipeTimeEditText.isVisible = false
        binding.recipeDifficultyEditText.isVisible = false
        binding.saveButton.isVisible = false
        binding.saveButton.isEnabled = false

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
            .setReorderingAllowed(true)
            .commit()
    }

    private fun showErrorState() {
        binding.progressBar.isVisible = false
        binding.errorImageView.isVisible = true
        binding.errorTextView.isVisible = true
        binding.successImageView.isVisible = false
        binding.recipeImageImageView.isVisible = false
        binding.recipeDetailsCardView.isVisible = false
        binding.recipeNameEditText.isVisible = false
        binding.recipeHeadlineEditText.isVisible = false
        binding.recipeDescriptionEditText.isVisible = false
        binding.recipeCaloriesEditText.isVisible = false
        binding.recipeProteinsEditText.isVisible = false
        binding.recipeFatsEditText.isVisible = false
        binding.recipeCarbsEditText.isVisible = false
        binding.recipeTimeEditText.isVisible = false
        binding.recipeDifficultyEditText.isVisible = false
        binding.saveButton.isVisible = false
        binding.saveButton.isEnabled = false
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}