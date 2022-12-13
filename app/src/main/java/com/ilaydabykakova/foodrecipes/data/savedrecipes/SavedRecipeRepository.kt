package com.ilaydabykakova.foodrecipes.data.savedrecipes

import androidx.lifecycle.LiveData
import com.ilaydabykakova.foodrecipes.models.Recipe

interface SavedRecipeRepository {

   fun getSavedRecipe() : LiveData<List<Recipe>>

   suspend fun savedDelete(recipe: Recipe)

   suspend fun savedUpsert(recipe:Recipe) : Long

}