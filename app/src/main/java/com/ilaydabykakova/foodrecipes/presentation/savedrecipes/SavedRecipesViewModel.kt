package com.ilaydabykakova.foodrecipes.presentation.savedrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilaydabykakova.foodrecipes.data.savedrecipes.SavedRecipeImpl
import com.ilaydabykakova.foodrecipes.models.Recipe
import kotlinx.coroutines.launch

class SavedRecipesViewModel constructor( private val savedRecipeRepository : SavedRecipeImpl): ViewModel()  {



    init {
        getSavedRecipe()
    }


    fun getSavedRecipe() = savedRecipeRepository.getSavedRecipe()


    fun saveRecipe(recipe: Recipe) = viewModelScope.launch {
        savedRecipeRepository.savedUpsert(recipe)
    }


    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        savedRecipeRepository.savedDelete(recipe)
    }


}