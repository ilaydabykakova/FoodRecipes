package com.ilaydabykakova.foodrecipes.domain.usecase

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