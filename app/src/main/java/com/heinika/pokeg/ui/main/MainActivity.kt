package com.heinika.pokeg.ui.main

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.databinding.ActivityMainBinding
import com.heinika.pokeg.ui.itemdelegate.PokemonItemDelegate
import com.heinika.pokeg.utils.RecyclerViewPaginator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MultiTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MultiTypeAdapter()
        adapter.register(PokemonItemDelegate())
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.let {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }

        viewModel.pokemonListLiveData.observe(this, {
            Timber.d("${it.size}")
            adapter.items = it
            adapter.notifyItemRangeChanged(0, it.size - 1)
        })

        viewModel.toastMessage.observe(this, { toastMessage->
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.isLoading.observe(this, { isLoading ->
            binding.progressbar.isVisible = isLoading
        })

        RecyclerViewPaginator(
            recyclerView = binding.recyclerView,
            isLoading = { if (viewModel.isLoading.value == null) true else viewModel.isLoading.value!! },
            loadMore = { viewModel.fetchNextPokemonList() },
            onLast = { false }
        ).run {
            threshold = 8
        }
    }
}