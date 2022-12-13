package com.ilaydabykakova.foodrecipes.data.recipes.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilaydabykakova.foodrecipes.models.Recipe


@Database(
    entities = [Recipe::class],
    version = 10
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao

    companion object{
        @Volatile
        private var instance: RecipeDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also { instance = it }
        }
        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, RecipeDatabase::class.java,"recipes_table"
        )
            .fallbackToDestructiveMigration()
            .build()

    }


}