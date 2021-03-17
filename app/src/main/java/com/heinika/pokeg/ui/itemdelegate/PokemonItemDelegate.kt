package com.heinika.pokeg.ui.itemdelegate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drakeet.multitype.ItemViewDelegate
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.heinika.pokeg.R
import com.heinika.pokeg.databinding.ItemPokemonBinding
import com.heinika.pokeg.model.Pokemon

class PokemonItemDelegate : ItemViewDelegate<Pokemon, PokemonItemDelegate.ViewHolder>() {

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Pokemon) {
        holder.setData(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPokemonBinding.bind(itemView)
        private lateinit var name: String

        init {
            binding.root.setOnClickListener {
                
            }
        }

        fun setData(item: Pokemon) {
            name = item.name

            with(binding) {
                nameLabel.text = item.name

                val imageUrl = item.getImageUrl()
                Glide.with(imageView)
                    .load(imageUrl)
                    .listener(
                        GlidePalette.with(imageUrl)
                            .use(BitmapPalette.Profile.MUTED_LIGHT)
                            .intoCallBack { palette ->
                                val rgb = palette?.dominantSwatch?.rgb
                                if (rgb != null) {
                                    cardView.setCardBackgroundColor(rgb)
                                }
                            }.crossfade(true)
                    ).into(imageView)
            }
        }
    }
}