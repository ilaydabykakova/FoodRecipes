package com.ilaydabykakova.foodrecipes.presentation.recipes


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ilaydabykakova.foodrecipes.R
import com.ilaydabykakova.foodrecipes.domain.uimodel.RecipeUIModel
import com.ilaydabykakova.foodrecipes.databinding.FragmentRecipesBinding
import com.ilaydabykakova.foodrecipes.models.Recipe
import com.ilaydabykakova.foodrecipes.domain.common.NetworkResult
import com.ilaydabykakova.foodrecipes.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private  val binding by viewBinding(FragmentRecipesBinding::bind)
    private val viewModels by viewModel<RecipesViewModel>()

    private val recipeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        RecipesAdapter (onSavedClick = { position: Int, recipe: Recipe ->
            viewModels.saveRecipe(recipe)

        }, onDeleteClick = { position: Int, recipe: Recipe ->
            viewModels.deleteRecipe(recipe)
            viewModels.getRecipe()
        })
    }

    val TAG = "BreakingRecipesList"



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

 /*
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        view.findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
 */

        observeLiveData()
        setupRecylerView()

    }


    private fun observeLiveData() {

        viewModels.itemListItemsLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Error -> {
                    hideProgressBar()
                    result.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }

                }

                is NetworkResult.Loading -> showProgressBar()

                is NetworkResult.Success -> {
                    hideProgressBar()
                    result.data?.let { response ->
                        response.apply {
                            recipeAdapter.items = response
                        }
                    }
                }
                else -> {}
            }
        }
    }

    private fun hideProgressBar() {
        binding.recipeProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.recipeProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecylerView() {

        binding.rvRecipesList.apply {

            val spanCount = 2

           layoutManager = GridLayoutManager(activity, spanCount, GridLayoutManager.VERTICAL, false)

            (layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val item = (adapter as RecipesAdapter).items.getOrNull(position) ?: return spanCount
                    return when (item) {
                        is RecipeUIModel.Title -> spanCount
                        is RecipeUIModel.SingleItem -> spanCount
                        is RecipeUIModel.Multitem -> spanCount / 2
                    }
                }
            }
            adapter = recipeAdapter
        }
    }

}

