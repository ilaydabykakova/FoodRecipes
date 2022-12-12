package com.ilaydabykakova.foodrecipes.data.di

import androidx.room.Room
import com.ilaydabykakova.foodrecipes.data.api.RecipeAPI
import com.ilaydabykakova.foodrecipes.data.api.RetrofitInceptor
import com.ilaydabykakova.foodrecipes.data.db.RecipeDatabase
import com.ilaydabykakova.foodrecipes.domain.repo.RecipeImpl
import com.ilaydabykakova.foodrecipes.domain.repo.RecipeRepository
import com.ilaydabykakova.foodrecipes.presentation.recipes.RecipesViewModel
import com.ilaydabykakova.foodrecipes.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appmodule = module {

   factory <RecipeRepository> { RecipeImpl(db= get<RecipeDatabase>(), service = get()) }
   factory <RecipeImpl> { get() }
   viewModel { RecipesViewModel(RecipeImpl(db = get<RecipeDatabase>(),get())) }



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
   factory { RetrofitInceptor() }
   factory { provideOkHttpClient(get()) }
   factory { provideRecipeApi(get()) }
   single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
   return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(recipeInceptor: RetrofitInceptor): OkHttpClient {
   return OkHttpClient().newBuilder().addInterceptor(recipeInceptor).build()
}

fun provideRecipeApi(retrofit: Retrofit): RecipeAPI = retrofit.create(RecipeAPI::class.java)


