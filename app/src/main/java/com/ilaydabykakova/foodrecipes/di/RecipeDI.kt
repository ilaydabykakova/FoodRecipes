package com.ilaydabykakova.foodrecipes.di

import androidx.room.Room
import com.ilaydabykakova.foodrecipes.data.recipes.api.RecipeAPI
import com.ilaydabykakova.foodrecipes.data.recipes.local.RecipeDatabase
import com.ilaydabykakova.foodrecipes.data.recipes.repo.RecipeImpl
import com.ilaydabykakova.foodrecipes.data.recipes.RecipeRepository
import com.ilaydabykakova.foodrecipes.data.savedrecipes.SavedRecipeImpl
import com.ilaydabykakova.foodrecipes.presentation.recipes.RecipesViewModel
import com.ilaydabykakova.foodrecipes.presentation.savedrecipes.SavedRecipesViewModel
import com.ilaydabykakova.foodrecipes.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appmodule = module {

   factory <RecipeRepository> { RecipeImpl(db= get<RecipeDatabase>(), service = get()) }
   factory <RecipeImpl> { get() }
   viewModel { RecipesViewModel(RecipeImpl(db = get<RecipeDatabase>(),get())) }
   viewModel { SavedRecipesViewModel(SavedRecipeImpl(db = get<RecipeDatabase>())) }

}

val roomModule = module {
   single {
      Room.databaseBuilder(
         androidContext(),
         RecipeDatabase::class.java, "recipes_table"
      ).fallbackToDestructiveMigration().build()
   }
}

val networkModule = module {

single <RecipeAPI> {

      val interceptor = HttpLoggingInterceptor()
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
      val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

      val retrofit = Retrofit.Builder()
         .baseUrl(BASE_URL)
         .client(client)
         .addConverterFactory(GsonConverterFactory.create())
         .build()

      retrofit.create(RecipeAPI::class.java)
   }

}

