package com.ilaydabykakova.foodrecipes.presentation.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilaydabykakova.foodrecipes.domain.uimodel.RecipeUIModel
import com.ilaydabykakova.foodrecipes.models.Recipe
import com.ilaydabykakova.foodrecipes.data.recipes.repo.RecipeImpl
import com.ilaydabykakova.foodrecipes.domain.common.NetworkResult
import kotlinx.coroutines.*


class RecipesViewModel constructor( private val recipeRepository : RecipeImpl): ViewModel() {


    private val _itemListItemsLiveData = MutableLiveData<NetworkResult<List<RecipeUIModel>>>()
    val itemListItemsLiveData: LiveData<NetworkResult<List<RecipeUIModel>>> = _itemListItemsLiveData


    init {

        getItemListItems()
    }


    fun saveRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeRepository.upsert(recipe)
    }

    fun getRecipe() =  recipeRepository.getSavedRecipe()


    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeRepository.delete(recipe)
    }



    private fun  getItemListItems() = viewModelScope.launch {
        _itemListItemsLiveData.postValue(NetworkResult.Loading())

        val singleDeferred = async {  recipeRepository.getOneRecipe()  }
        val multiDeferred = async { recipeRepository.getMultipleRecipe() }

        val singleRecipe = singleDeferred.await()
        val multiRecipe = multiDeferred.await()

        val homeItemsList = mutableListOf<RecipeUIModel>()
        if (singleRecipe is NetworkResult.Success && multiRecipe is NetworkResult.Success) {

            homeItemsList.add(RecipeUIModel.Title("Recommended Recipes"))

            for(item in singleRecipe.data?.recipes.orEmpty()) {
                homeItemsList.add(RecipeUIModel.SingleItem(item))
            }
            homeItemsList.add(RecipeUIModel.Title("Weekly Recipes"))

            for(item in multiRecipe.data?.recipes.orEmpty()){
                homeItemsList.add(RecipeUIModel.Multitem(item))
            }

            _itemListItemsLiveData.postValue(NetworkResult.Success(homeItemsList))
        }
        else{
            NetworkResult.Error("Invalid",null)
        }
    }

    }




