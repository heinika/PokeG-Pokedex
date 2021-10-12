package com.heinika.pokeg.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.heinika.pokeg.R
import com.heinika.pokeg.utils.PokemonProp
import com.heinika.pokeg.utils.dp

@SuppressLint("ViewConstructor")
class TypeCheckBox @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    type:PokemonProp.Type,
) : AppCompatCheckBox(context, attrs) {
    init {
        tag = type.name
        textAlignment = TEXT_ALIGNMENT_CENTER
        text = type.getName(context)
        setTextColor(Color.WHITE)
        buttonDrawable = null
        val drawable = resources.getDrawable(R.drawable.check_box_type_normal) as GradientDrawable
        drawable.setTint(type.getColor(context))
        drawable.setTintMode(PorterDuff.Mode.ADD)
        setBackgroundDrawable(drawable)
        setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                (background as GradientDrawable).setStroke(2.dp, Color.RED)
            } else {
                (background as GradientDrawable).setStroke(0, Color.RED)
            }
        }
    }
}