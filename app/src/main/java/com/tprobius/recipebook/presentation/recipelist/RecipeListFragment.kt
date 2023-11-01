package com.tprobius.recipebook.presentation.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tprobius.recipebook.R
import com.tprobius.recipebook.databinding.FragmentRecipeListBinding
import com.tprobius.recipebook.domain.entities.RecipeItem
import com.tprobius.recipebook.presentation.recipeadding.RecipeAddingFragment
import com.tprobius.recipebook.presentation.recipedetails.RecipeDetailsFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment() {
    private var _binding: FragmentRecipeListBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: RecipeListViewModel by viewModel()

    private lateinit var recipeListAdapter: RecipeItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRecipeList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        setRecipeListAdapter()
        setOnAddClick()
    }

    private fun handleState(state: RecipeListState) {
        when (state) {
            RecipeListState.Initial -> showInitialState()
            RecipeListState.Loading -> showLoadingState()
            is RecipeListState.Success -> showSuccessState(state.recipeList)
            RecipeListState.Error -> showErrorState()
        }
    }

    private fun setRecipeListAdapter() {
        recipeListAdapter = RecipeItemAdapter { navigateToRecipeDetailsFragment(it) }
        binding.recipeListRecyclerView.adapter = recipeListAdapter
    }

    private fun navigateToRecipeDetailsFragment(recipeListItem: RecipeItem) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_main, RecipeDetailsFragment.newInstance(recipeListItem))
            .setReorderingAllowed(true)
            .commit()
    }

    private fun setViewsVisibility(
        progressBarIsVisible: Boolean = false,
        recyclerViewIsVisible: Boolean = false,
        errorImageViewIsVisible: Boolean = false,
        errorTextViewIsVisible: Boolean = false
    ) {
        with(binding) {
            progressBar.isVisible = progressBarIsVisible
            recipeListRecyclerView.isVisible = recyclerViewIsVisible
            errorImageView.isVisible = errorImageViewIsVisible
            errorTextView.isVisible = errorTextViewIsVisible
        }
    }

    private fun showInitialState() {
        setViewsVisibility()
    }

    private fun showLoadingState() {
        setViewsVisibility(progressBarIsVisible = true)
    }

    private fun showSuccessState(recipeList: List<RecipeItem>) {
        setViewsVisibility(recyclerViewIsVisible = true)
        viewLifecycleOwner.lifecycleScope.launch {
            recipeListAdapter.apply {
                items = recipeList
            }
        }
    }

    private fun showErrorState() {
        setViewsVisibility(errorImageViewIsVisible = true, errorTextViewIsVisible = true)
    }

    private fun setOnAddClick() {
        binding.addNewFloatingActionButton.setOnClickListener {
            navigateToRecipeAddingFragment()
        }
    }

    private fun navigateToRecipeAddingFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_main, RecipeAddingFragment())
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}