package com.ilaydabykakova.foodrecipes.presentation.recipes


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilaydabykakova.foodrecipes.R
import com.ilaydabykakova.foodrecipes.databinding.ItemRecipesBinding
import com.ilaydabykakova.foodrecipes.databinding.ItemTitleBinding
import com.ilaydabykakova.foodrecipes.databinding.MultipleItemRecipesBinding
import com.ilaydabykakova.foodrecipes.domain.uimodel.RecipeUIModel
import com.ilaydabykakova.foodrecipes.models.Recipe


class RecipesAdapter(val onSavedClick: (position : Int,result: Recipe) -> Unit,
                     val onDeleteClick: (position : Int,result: Recipe) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        inner class TitleViewHolder(private val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(title: RecipeUIModel.Title) {
                binding.recipeTopTitle.text = title.title
            }
        }

        inner class SingleRecipeViewHolder(private val binding: ItemRecipesBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(recipe: RecipeUIModel.SingleItem) {

                    val response = recipe.singleRecipe
                    binding.recipe = recipe.singleRecipe

                     binding.saveSingleRecipe.setOnClickListener {
                         onSavedClick.invoke(adapterPosition,response)
                         binding.saveSingleRecipe.visibility = View.INVISIBLE
                         binding.savedSingleRecipe.visibility = View.VISIBLE
                }

                   binding.savedSingleRecipe.setOnClickListener{
                       onDeleteClick.invoke(adapterPosition,response)
                       binding.saveSingleRecipe.visibility = View.VISIBLE
                       binding.savedSingleRecipe.visibility = View.INVISIBLE
                   }

            }
        }

        inner class MultiRecipeViewHolder(private val binding: MultipleItemRecipesBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(recipe: RecipeUIModel.Multitem) {
                val response = recipe.multiRecipe
                binding.recipe = recipe.multiRecipe
                binding.saveMultipleRecipe.setOnClickListener {
                    onSavedClick.invoke(adapterPosition, response)
                    binding.saveMultipleRecipe.visibility = View.INVISIBLE
                    binding.savedMultipleRecipe.visibility = View.VISIBLE
                }
                binding.savedMultipleRecipe.setOnClickListener{
                    onDeleteClick.invoke(adapterPosition,response)
                    binding.saveMultipleRecipe.visibility = View.VISIBLE
                    binding.savedMultipleRecipe.visibility = View.INVISIBLE
                }
            }
        }

        var items = listOf<RecipeUIModel>()
            @SuppressLint("NotifyDataSetChanged")
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
            return when(viewType){
                R.layout.item_title ->  TitleViewHolder(
                    ItemTitleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
                R.layout.item_recipes -> SingleRecipeViewHolder(
                    ItemRecipesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
                R.layout.multiple_item_recipes -> MultiRecipeViewHolder(
                    MultipleItemRecipesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
                else -> throw IllegalArgumentException("Invalid ViewType Provided")
            }
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TitleViewHolder -> holder.bind(items[position] as RecipeUIModel.Title)
            is SingleRecipeViewHolder -> holder.bind(items[position] as RecipeUIModel.SingleItem)
            is MultiRecipeViewHolder -> holder.bind(items[position] as RecipeUIModel.Multitem)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is RecipeUIModel.Title -> R.layout.item_title
            is RecipeUIModel.SingleItem -> R.layout.item_recipes
            is RecipeUIModel.Multitem -> R.layout.multiple_item_recipes
            }
        }


}

