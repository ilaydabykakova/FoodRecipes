package com.ilaydabykakova.foodrecipes.domain.usecase

import androidx.lifecycle.Observer
import com.ilaydabykakova.foodrecipes.domain.repo.RecipeImpl
import com.ilaydabykakova.foodrecipes.domain.uimodel.RecipeUIModel
import com.ilaydabykakova.foodrecipes.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/*
class GetSingleRecipeUIModelUseCase constructor(private val recipeRepository : RecipeImpl) {

    fun executeGetCharacters(coroutineScope: CoroutineScope): NetworkResult<RecipeUIModel.SingleItem> {
            try {

                NetworkResult.Loading<RecipeUIModel.SingleItem>()
                val response = recipeRepository.getOneRecipe()
                if (response.data?.recipes.isNullOrEmpty()) {

                    NetworkResult.Error(message = "No Rick And Morty Character", null)
                } else {
                    recipeRepository.getOneRecipe().data
                }

            } catch (e: HttpException) {
                NetworkResult.Error(message = e.localizedMessage ?: "Error!", null)
            } catch (e: IOException) {
                NetworkResult.Error(message = "Check Your Internet", null)
            }
    }

}
 */