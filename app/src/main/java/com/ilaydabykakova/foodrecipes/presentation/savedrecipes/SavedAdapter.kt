package com.ilaydabykakova.foodrecipes.presentation.savedrecipes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ilaydabykakova.foodrecipes.R
import com.ilaydabykakova.foodrecipes.databinding.ItemRecipesBinding
import com.ilaydabykakova.foodrecipes.models.Recipe

import kotlinx.android.synthetic.main.item_recipes.view.*

class SavedAdapter(): ListAdapter<Recipe,RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object  {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }

        }
    }

    var items = listOf<Recipe>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class RecipeViewHolder(private val binding: ItemRecipesBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val response = items.get(position)
            binding.recipe = response

            binding.saveSingleRecipe.visibility = View.INVISIBLE

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when(viewType){
           R.layout.item_recipes -> RecipeViewHolder(ItemRecipesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
           else -> throw IllegalArgumentException("Invalid")
       }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is RecipeViewHolder -> holder.bind(position)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_recipes

    }
}