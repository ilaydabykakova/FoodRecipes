package com.ilaydabykakova.foodrecipes.data.savedrecipes

import androidx.lifecycle.LiveData
import com.ilaydabykakova.foodrecipes.data.recipes.local.RecipeDatabase
import com.ilaydabykakova.foodrecipes.models.Recipe

class SavedRecipeImpl constructor(private val db: RecipeDatabase) : SavedRecipeRepository {


    override fun getSavedRecipe(): LiveData<List<Recipe>> {
        return db.getRecipeDao().getAllRecipes()
    }


    override suspend fun savedDelete(recipe: Recipe) {
        return db.getRecipeDao().deleteRecipe(recipe)
    }

    override suspend fun savedUpsert(recipe: Recipe): Long {
        return db.getRecipeDao().upsert(recipe)
    }


}