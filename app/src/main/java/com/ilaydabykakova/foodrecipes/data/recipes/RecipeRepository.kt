package com.ilaydabykakova.foodrecipes.data.recipes

import androidx.lifecycle.LiveData
import com.ilaydabykakova.foodrecipes.models.Recipe
import com.ilaydabykakova.foodrecipes.models.RecipeResponse
import com.ilaydabykakova.foodrecipes.domain.common.NetworkResult

interface RecipeRepository{

    suspend fun getOneRecipe() : NetworkResult<RecipeResponse>

    suspend fun getMultipleRecipe() : NetworkResult<RecipeResponse>

    suspend fun upsert(recipe:Recipe) : Long

    fun getSavedRecipe() :LiveData<List<Recipe>>

    suspend fun delete(recipe: Recipe)


}
