package com.ilaydabykakova.foodrecipes.presentation.recipedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ilaydabykakova.foodrecipes.R
import com.ilaydabykakova.foodrecipes.databinding.FragmentRecipeDetailsBinding
import com.ilaydabykakova.foodrecipes.databinding.FragmentRecipesBinding
import com.ilaydabykakova.foodrecipes.presentation.recipes.RecipesViewModel
import com.ilaydabykakova.foodrecipes.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeDetails : Fragment(R.layout.fragment_recipe_details) {

    private  val binding by viewBinding(FragmentRecipeDetailsBinding::bind)
    private val viewModel by viewModel<RecipesViewModel>()
    val args: RecipeDetailsArgs by navArgs()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recipe = args.recipe


    }

}