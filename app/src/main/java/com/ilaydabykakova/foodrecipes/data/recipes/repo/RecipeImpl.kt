package com.ilaydabykakova.foodrecipes.data.recipes.repo

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.ilaydabykakova.foodrecipes.data.recipes.RecipeRepository
import com.ilaydabykakova.foodrecipes.data.recipes.api.RecipeAPI
import com.ilaydabykakova.foodrecipes.data.recipes.local.RecipeDatabase
import com.ilaydabykakova.foodrecipes.models.Recipe
import com.ilaydabykakova.foodrecipes.models.RecipeResponse
import com.ilaydabykakova.foodrecipes.domain.common.NetworkResult

class RecipeImpl constructor(private val db: RecipeDatabase, private val service: RecipeAPI):
    RecipeRepository {

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getOneRecipe(): NetworkResult<RecipeResponse> {
        val result = service.getOneRecipe()

           return if (result.isSuccessful){
                result.body()?.let { response ->
                    NetworkResult.Success(response)
                }?: NetworkResult.Error(result.message())
            }else {
                result.body()?.let { response ->
                    NetworkResult.Error(result.message(),response)
                }?: NetworkResult.Error(result.message())
            }

    }

    override suspend fun getMultipleRecipe(): NetworkResult<RecipeResponse> {
        val result = service.getMultipleRecipes()
        return if (result.isSuccessful){
            result.body()?.let { response ->
                NetworkResult.Success(response)
            }?: NetworkResult.Error(result.message())
        }else {
            result.body()?.let { response ->
                NetworkResult.Error(result.message(),response)
            }?: NetworkResult.Error(result.message())
        }
    }

    override suspend fun upsert(recipe: Recipe): Long {
        return db.getRecipeDao().upsert(recipe)
    }

    override fun getSavedRecipe(): LiveData<List<Recipe>> {
        return db.getRecipeDao().getAllRecipes()
    }

    override suspend fun delete(recipe: Recipe) {
        return db.getRecipeDao().deleteRecipe(recipe)
    }

}