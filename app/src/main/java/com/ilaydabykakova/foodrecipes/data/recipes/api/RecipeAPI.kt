package com.ilaydabykakova.foodrecipes.data.recipes.api

import com.ilaydabykakova.foodrecipes.utils.Constants.Companion.API_KEY
import com.ilaydabykakova.foodrecipes.models.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeAPI {

    @GET("recipes/random")
    suspend fun getOneRecipe( @Query("apiKey") apiKey: String = API_KEY, @Query("number") number: Int = 1 ): Response<RecipeResponse>

    @GET("recipes/random")
    suspend fun getMultipleRecipes( @Query("apiKey") apiKey: String = API_KEY, @Query("number") number: Int = 21): Response<RecipeResponse>
/*
    @GET("recipes/complexSearch")
    suspend fun searchRecipeByName(@Query("apiKey") apiKey: String = API_KEY, @Query("query") searchQuery: String): Response<Recipe>
 */
}