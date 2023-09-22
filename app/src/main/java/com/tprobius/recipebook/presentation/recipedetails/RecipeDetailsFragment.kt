package com.tprobius.recipebook.presentation.recipedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.tprobius.recipebook.data.entites.RecipeListItem
import com.tprobius.recipebook.databinding.FragmentRecipeDetailsBinding
import com.tprobius.recipebook.utils.FillSpace
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailsFragment : Fragment() {
    private var _binding: FragmentRecipeDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding isn't initialized" }

    private val viewModel: RecipeDetailsViewModel by viewModel()

    private lateinit var recipeItem: RecipeListItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            recipeItem = it.recipe as RecipeListItem
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        viewModel.getRecipeDetails(recipeItem)

        setRecipeItem()
        setHandleState()
    }

    private fun handleState(state: RecipeDetailsState) {
        when (state) {
            RecipeDetailsState.Initial -> showInitialState()
            RecipeDetailsState.Loading -> showLoadingState()
            is RecipeDetailsState.Success -> showSuccessState(state.recipeItem)
            RecipeDetailsState.Error -> showErrorState()
        }
    }

    private fun setRecipeItem() {
        binding.recipeItem = recipeItem
        Glide.with(binding.recipeImageImageView)
            .load(recipeItem.image)
            .transform(MultiTransformation(FillSpace(), RoundedCorners(8)))
            .into(binding.recipeImageImageView)
    }

    private fun setHandleState() {
        viewModel.state.observe(viewLifecycleOwner) {
            handleState(it)
        }
    }

    private fun showInitialState() {

    }

    private fun showLoadingState() {

    }

    private fun showSuccessState(recipeItem: RecipeListItem) {

    }

    private fun showErrorState() {

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val RECIPE_KEY = "recipe_key"

//        private var Bundle.recipeName
//            get() = getString(RECIPE_KEY)
//            set(value) = putString(RECIPE_KEY, value)


        var Bundle.recipe
            get() = getSerializable(RECIPE_KEY)
            set(value) = putSerializable(RECIPE_KEY, value)

        fun newInstance(recipeItem: RecipeListItem) = RecipeDetailsFragment().apply {
            arguments = Bundle().apply {
                this.recipe = recipeItem
                Log.d("WTF?!?!?!?!?!?!", "${this.recipe}")
            }
        }

//        fun newInstance(hotelName: String) = RecipeDetailsFragment().apply {
//            arguments = Bundle().apply { this.hotelName = hotelName }
//        }


////
//        private val DESCRIBABLE_KEY = "describable_key"
//        private var mDescribable: Describable? = null
//
//        fun newInstance(recipeItem: RecipeListItem): RecipeDetailsFragment {
//            val fragment = RecipeDetailsFragment()
//            val bundle = Bundle()
//            bundle.putSerializable(RECIPE_KEY, recipeItem)
//            fragment.arguments = bundle
//            return fragment
//        }


    }
}
