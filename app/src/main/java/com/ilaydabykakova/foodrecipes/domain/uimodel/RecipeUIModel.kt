package com.ilaydabykakova.foodrecipes.domain.uimodel

import com.ilaydabykakova.foodrecipes.models.Recipe

sealed class RecipeUIModel {

    class SingleItem(
        val singleRecipe: Recipe
    ) : RecipeUIModel()
    class Multitem(
        val multiRecipe: Recipe
    ) : RecipeUIModel()

    class Title(
        val title: String
    ) : RecipeUIModel()

}