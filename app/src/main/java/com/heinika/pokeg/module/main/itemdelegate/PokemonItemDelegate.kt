package com.heinika.pokeg.module.main.itemdelegate

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.drakeet.multitype.ItemViewDelegate
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.BuildConfig
import com.github.florent37.glidepalette.GlidePalette
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.R
import com.heinika.pokeg.databinding.ItemPokemonBinding
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.utils.BlurTransformation
import com.heinika.pokeg.utils.ImageUtils

class PokemonItemDelegate(
  private val pokemonRes: PokemonRes,
  private val onItemClick: (AppCompatImageView, Pokemon) -> Unit,
  private val onFavoriteClick: (Pokemon, isChecked: Boolean) -> Unit
) :
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
      binding.favoriteCheckBox.setOnClickListener {
        onFavoriteClick(item, binding.favoriteCheckBox.isChecked)
      }
    }

    fun setData(pokemon: Pokemon) {
      this.item = pokemon

      with(binding) {
        nameLabel.text = pokemonRes.getNameById(pokemon.id, pokemon.name, pokemon.form)
        favoriteCheckBox.isChecked = ConfigMMKV.favoritePokemons.contains(pokemon.id.toString())
        ImageUtils.loadImage(imageView,pokemon.getImageUrl())
        refreshInfo(pokemon)
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
          val typeId1 = pokemon.types[0]
          type1Text.text = pokemonRes.getTypeString(typeId1)
          type1Text.background.setTint(pokemonRes.getTypeColor(typeId1))
        }
        2 -> {
          type1Text.isVisible = true
          type2Text.isVisible = true
          val typeId1 = pokemon.types[0]
          type1Text.text = pokemonRes.getTypeString(typeId1)
          type1Text.background.setTint(pokemonRes.getTypeColor(typeId1))
          val typeId2 = pokemon.types[1]
          type2Text.text = pokemonRes.getTypeString(typeId2)
          type2Text.background.setTint(pokemonRes.getTypeColor(typeId2))
        }
      }

      if (pokemon.id != 0) {
        idText.isVisible = true
        idText.text = pokemon.getFormatId()
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