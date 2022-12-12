package com.ilaydabykakova.foodrecipes.presentation.savedrecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ilaydabykakova.foodrecipes.R
import com.ilaydabykakova.foodrecipes.databinding.FragmentRecipesBinding
import com.ilaydabykakova.foodrecipes.databinding.FragmentSavedRecipesBinding
import com.ilaydabykakova.foodrecipes.presentation.recipes.RecipesViewModel
import com.ilaydabykakova.foodrecipes.utils.viewBinding
import kotlinx.android.synthetic.main.fragment_saved_recipes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedRecipesFragment : Fragment(R.layout.fragment_saved_recipes) {

    private  val binding by viewBinding(FragmentSavedRecipesBinding::bind)
    private val viewModel by viewModel<RecipesViewModel>()
    lateinit var recipesAdapter: SavedAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSingleRecyclerView()
        swipeUp()

    }

    private fun swipeUp(){
        val itemTouchHelperCallback = object :ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val recipe = recipesAdapter.items[position]
                viewModel.deleteRecipe(recipe)
                view?.let {
                    Snackbar.make(it,"Successfully deleted recipe", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo"){
                            viewModel.saveRecipe(recipe)
                        }.show()
                    }
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedRecipes)
        }
        viewModel.getSavedRecipe().observe(viewLifecycleOwner, Observer { recipe ->
            recipesAdapter.items = recipe
        })
    }

    private fun setupSingleRecyclerView() {
        recipesAdapter = SavedAdapter()
        binding.rvSavedRecipes.apply {
            adapter = recipesAdapter
            layoutManager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        }
    }



}