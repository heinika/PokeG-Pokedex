package com.heinika.pokeg.ui.detail

import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.heinika.pokeg.R
import com.heinika.pokeg.databinding.ActivityDetailBinding
import com.heinika.pokeg.databinding.ActivityMainBinding
import com.heinika.pokeg.model.PokemonInfo.Companion.maxHp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressHp.max = maxHp.toFloat()
        viewModel.pokemonInfoLiveData.observe(this) {
            binding.index.text = it.getIdString()
            binding.weight.text = it.getWeightString()
            binding.height.text = it.getHeightString()
            binding.progressHp.progress = it.hp.toFloat()
        }

        binding.arrow.setOnClickListener {
            finish()
        }
    }
}