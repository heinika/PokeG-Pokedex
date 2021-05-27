package com.heinika.pokeg.ui.main.itemdelegate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drakeet.multitype.ItemViewDelegate
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.heinika.pokeg.R
import com.heinika.pokeg.databinding.ItemPokemonBinding
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.ui.main.MainViewModel
import com.heinika.pokeg.utils.PokemonRes

class PokemonItemDelegate(private val pokemonRes: PokemonRes, val lifecycleOwner: LifecycleOwner, private val mainViewModel: MainViewModel, private val onItemClick: (AppCompatImageView, Pokemon) -> Unit) :
  ItemViewDelegate<Pokemon, PokemonItemDelegate.ViewHolder>() {

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, item: Pokemon) {
    holder.setData(item)
  }

  override fun onBindViewHolder(holder: ViewHolder, item: Pokemon, payloads: List<Any>) {
    holder.setData(item)
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPokemonBinding.bind(itemView)
    private lateinit var item: Pokemon

    init {
      binding.root.setOnClickListener {
        onItemClick(binding.imageView, item)
      }
    }

    fun setData(pokemon: Pokemon) {
      this.item = pokemon

      with(binding) {
        nameLabel.text = pokemonRes.getNameById(pokemon.id, pokemon.name)

        val imageUrl = pokemon.getImageUrl()
        Glide.with(imageView)
          .load(imageUrl)
          .listener(
            GlidePalette.with(imageUrl)
              .use(BitmapPalette.Profile.MUTED_LIGHT)
              .intoCallBack { palette ->
                val rgb = palette?.dominantSwatch?.rgb
                if (rgb != null) {
                  imageView.background.setTint(rgb)
                }
              }.crossfade(true)
          ).into(imageView)

        refreshInfo(pokemon)
        mainViewModel.fetchUpdatePokemonLiveData(pokemon).observe(lifecycleOwner) {
          if (layoutPosition == it.id - 1) {
            refreshInfo(it)
          }
        }
      }
    }

    private fun ItemPokemonBinding.refreshInfo(pokemon: Pokemon) {
      when (pokemon.types.size) {
        0 -> {
          type1Text.visibility = View.INVISIBLE
          type2Text.visibility = View.INVISIBLE
        }
        1 -> {
          type1Text.isVisible = true
          type2Text.visibility = View.INVISIBLE
          val typeName1 = pokemon.types[0].type.name
          type1Text.text = pokemonRes.getTypeString(typeName1)
          type1Text.background.setTint(pokemonRes.getTypeColor(typeName1))
        }
        2 -> {
          type1Text.isVisible = true
          type2Text.isVisible = true
          val typeName1 = pokemon.types[0].type.name
          type1Text.text = pokemonRes.getTypeString(typeName1)
          type1Text.background.setTint(pokemonRes.getTypeColor(typeName1))
          val typeName2 = pokemon.types[1].type.name
          type2Text.text = pokemonRes.getTypeString(typeName2)
          type2Text.background.setTint(pokemonRes.getTypeColor(typeName2))
        }
      }

      if (pokemon.id != 0) {
        idText.isVisible = true
        idText.text = pokemon.getIdString()
      } else {
        idText.isVisible = false
      }

      if (pokemon.totalBaseStat != 0) {
        totalStatText.isVisible = true
        totalStatText.text = pokemon.totalBaseStat.toString()
      } else {
        totalStatText.isVisible = false
      }
    }
  }
}