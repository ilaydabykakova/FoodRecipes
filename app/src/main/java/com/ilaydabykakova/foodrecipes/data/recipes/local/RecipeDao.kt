package com.ilaydabykakova.foodrecipes.data.recipes.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ilaydabykakova.foodrecipes.models.Recipe

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipe: Recipe) : Long

    @Query("SELECT * FROM recipes_table")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Update
    fun updateRecipe(recipe: List<Recipe>)
}