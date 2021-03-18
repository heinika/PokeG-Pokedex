package com.heinika.pokeg.ui.detail

import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.heinika.pokeg.CurPokemon
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {


    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        val textview = TextView(this)
        setContentView(textview)

        viewModel.pokemonInfoLiveData.observe(this) {
            textview.text = it.toString()
        }
    }
}