package com.ilaydabykakova.foodrecipes.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ilaydabykakova.foodrecipes.domain.uimodel.RecipeUIModel
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Keep
data class RecipeResponse(
    @SerializedName("recipes")
    @Expose
    @Embedded val recipes: List<Recipe>
):Serializable


@Entity(
    tableName = "recipes_table"
)
@Keep
data class Recipe(
    @ColumnInfo(name = "creditsText")
    @SerializedName("creditsText")
    val creditsText: String,

    @ColumnInfo(name = "aggregateLikes")
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String,
    @ColumnInfo(name = "imageType")
    @SerializedName("imageType")
    val imageType: String,
    @ColumnInfo(name = "instructions")
    @SerializedName("instructions")
    val instructions: String,
    @ColumnInfo(name = "readyInMinutes")
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
    @ColumnInfo(name = "servings")
    @SerializedName("servings")
    val servings: Int,
    @ColumnInfo(name = "summary")
    @SerializedName("summary")
    val summary: String,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
    @ColumnInfo(name = "vegan")
    @SerializedName("vegan")
    val vegan: Boolean,
):Serializable
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int? = null
}

/*
fun Recipe.toRecipeUIModel(): RecipeUIModel {

    return RecipeUIModel.SingleItem(
        Recipe(creditsText, aggregateLikes, id, image, imageType, instructions, readyInMinutes, servings, summary, title, vegan)
    )
}
fun Recipe.toRecipeMultiUIModel(): RecipeUIModel.Multitem {

    return RecipeUIModel.Multitem(
        Recipe(creditsText, aggregateLikes, id, image, imageType, instructions, readyInMinutes, servings, summary, title, vegan)
    )
}

 */
