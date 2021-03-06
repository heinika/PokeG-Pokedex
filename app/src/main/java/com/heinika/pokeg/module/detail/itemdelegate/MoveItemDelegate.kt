package com.heinika.pokeg.module.detail.itemdelegate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.heinika.pokeg.R
import com.heinika.pokeg.databinding.ItemPokemonMoveBinding
import com.heinika.pokeg.module.detail.itemdelegate.model.MoveItem
import com.heinika.pokeg.repository.res.PokemonRes

class MoveItemDelegate(private val pokemonRes: PokemonRes) :
  ItemViewDelegate<MoveItem, MoveItemDelegate.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder, item: MoveItem) {
    holder.setData(item)
  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(context).inflate(R.layout.item_pokemon_move, parent, false)
    )
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPokemonMoveBinding.bind(itemView)

    fun setData(item: MoveItem) {
      binding.moveNameText.text = item.name
      binding.moveTypeText.text = item.type
      binding.moveTypeText.background.setTint(item.typeColor)
      binding.moveDamageClassText.text = item.damageClass
      binding.moveAccuracyText.text = if (item.accuracy == 0) "--" else item.accuracy.toString()
      binding.movePowerText.text = if (item.power == 0) "--" else item.power.toString()
      binding.movePPText.text = item.pp.toString()
      binding.moveLevelText.text = binding.moveLevelText.resources.getString(R.string.level_text,item.level)
    }
  }
}