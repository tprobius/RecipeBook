package com.tprobius.recipebook.presentation.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.tprobius.recipebook.R
import com.tprobius.recipebook.databinding.FragmentRecipeListBinding
import com.tprobius.recipebook.domain.entities.ListItem
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

    private lateinit var recipeListAdapter: ListDelegationAdapter<List<ListItem>>

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
        viewModel.getRecipeList()
//        setHandleState()
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

//    private fun setHandleState() {
//        viewModel.state.observe(viewLifecycleOwner) {
//            handleState(it)
//        }
//    }

    private fun setRecipeListAdapter() {
        recipeListAdapter = ListDelegationAdapter(recipeItemDelegate {
            navigateToRecipeDetailsFragment(it)
        })
        binding.recipeListRecyclerView.adapter = recipeListAdapter
    }

    private fun navigateToRecipeDetailsFragment(recipeListItem: RecipeItem) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_main, RecipeDetailsFragment.newInstance(recipeListItem))
            .setReorderingAllowed(true)
            .commit()
    }

    private fun showInitialState() {
        binding.progressBar.isVisible = false
        binding.recipeListRecyclerView.isVisible = false
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
    }

    private fun showLoadingState() {
        binding.progressBar.isVisible = true
        binding.recipeListRecyclerView.isVisible = false
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
    }

    private fun showSuccessState(recipeList: List<RecipeItem>) {
        binding.progressBar.isVisible = false
        binding.recipeListRecyclerView.isVisible = true
        binding.errorImageView.isVisible = false
        binding.errorTextView.isVisible = false
        viewLifecycleOwner.lifecycleScope.launch {
            recipeListAdapter.apply {
                items = recipeList
                notifyDataSetChanged()
            }
        }
    }

    private fun showErrorState() {
        binding.progressBar.isVisible = false
        binding.recipeListRecyclerView.isVisible = false
        binding.errorImageView.isVisible = true
        binding.errorTextView.isVisible = true
    }

    private fun setOnAddClick() {
        binding.addScreenImageView.setOnClickListener {
            navigateToRecipeAddingFragment()
        }
    }

    private fun navigateToRecipeAddingFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_main, RecipeAddingFragment())
            .setReorderingAllowed(true)
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}