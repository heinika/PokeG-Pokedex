package com.heinika.pokeg.ui.detail

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.heinika.pokeg.CurPokemon
import com.heinika.pokeg.databinding.ActivityDetailBinding
import com.heinika.pokeg.model.PokemonInfo.Companion.maxAttack
import com.heinika.pokeg.model.PokemonInfo.Companion.maxDefense
import com.heinika.pokeg.model.PokemonInfo.Companion.maxHp
import com.heinika.pokeg.model.PokemonInfo.Companion.maxSpecialAttack
import com.heinika.pokeg.model.PokemonInfo.Companion.maxSpecialDefense
import com.heinika.pokeg.model.PokemonInfo.Companion.maxSpeed
import com.heinika.pokeg.utils.PokemonTypeUtils
import com.heinika.pokeg.utils.SpacesItemDecoration
import com.skydoves.androidribbon.ribbonView
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

    binding.progressHp.max = maxHp
    binding.progressAttach.max = maxAttack
    binding.progressDefense.max = maxDefense
    binding.progressSpAttack.max = maxSpecialAttack
    binding.progressSpDefense.max = maxSpecialDefense
    binding.progressSpd.max = maxSpeed

    viewModel.pokemonInfoLiveData.observe(this) {
      binding.index.text = it.getIdString()
      binding.name.text = it.name
      binding.weight.text = it.getWeightString()
      binding.height.text = it.getHeightString()
      binding.progressHp.progress = it.hp.toFloat()
      binding.progressHp.labelText = it.hp.toString()
      binding.progressAttach.progress = it.attack.toFloat()
      binding.progressAttach.labelText = it.attack.toString()
      binding.progressDefense.progress = it.defense.toFloat()
      binding.progressDefense.labelText = it.defense.toString()
      binding.progressSpAttack.progress = it.specialAttack.toFloat()
      binding.progressSpAttack.labelText = it.specialAttack.toString()
      binding.progressSpDefense.progress = it.specialDefense.toFloat()
      binding.progressSpDefense.labelText = it.specialDefense.toString()
      binding.progressSpd.progress = it.speed.toFloat()
      binding.progressSpd.labelText = it.speed.toString()

      Glide.with(binding.image)
        .load(CurPokemon.imageUrl)
        .listener(
          GlidePalette.with(CurPokemon.imageUrl)
            .use(BitmapPalette.Profile.MUTED_LIGHT)
            .intoCallBack { palette ->
              val rgb = palette?.dominantSwatch?.rgb
              if (rgb != null) {
                binding.header.setBackgroundColor(rgb)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = rgb
              }
            }.crossfade(true)
        ).into(binding.image)

      for (type in it.types) {
        with(binding.ribbonRecyclerView) {
          addRibbon(
            ribbonView(context) {
              setText(type.type.name)
              setTextColor(Color.WHITE)
              setPaddingLeft(84f)
              setPaddingRight(84f)
              setPaddingTop(2f)
              setPaddingBottom(10f)
              setTextSize(16f)
              setRibbonRadius(120f)
              setTextStyle(Typeface.BOLD)
              setRibbonBackgroundColorResource(
                PokemonTypeUtils.getTypeColor(type.type.name)
              )
            }.apply {
              maxLines = 1
              gravity = Gravity.CENTER
            }
          )
          addItemDecoration(SpacesItemDecoration())
        }
      }
    }

    viewModel.isLoading.observe(this) { isLoading ->
      binding.progressbar.isVisible = isLoading
    }

    viewModel.toastMessage.observe(this) { toastMessage ->
      Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_LONG).show()
    }

    binding.arrow.setOnClickListener {
      finish()
    }
  }
}