package com.heinika.pokeg.ui.main.layout

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatCheckBox
import com.google.android.flexbox.AlignContent.SPACE_AROUND
import com.google.android.flexbox.FlexWrap.WRAP
import com.google.android.flexbox.FlexboxLayout
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout
import com.heinika.pokeg.utils.PokemonProp
import com.heinika.pokeg.view.TypeCheckBox
import timber.log.Timber
import java.util.*

class FilterListView(context: Context) : CustomLayout(context) {

    var onSelectedChange: ((List<PokemonProp.Type>) -> Unit)? = null

    init {
        setBackgroundColor(context.getColor(R.color.background))
    }

    private val typeQueue = LinkedList<PokemonProp.Type>()
    private val checkBoxList = arrayListOf<AppCompatCheckBox>()

    private val flexboxLayout = FlexboxLayout(context).apply {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        alignContent = SPACE_AROUND
        setPadding(8.dp, 0, 8.dp, 0)
        flexWrap = WRAP
        PokemonProp.Type.values().forEach {
            if (it != PokemonProp.Type.UNKNOWN) {
                addTypeCheckBox(this, context, it)
            }
        }

        this@FilterListView.addView(this)
    }


    private fun addTypeCheckBox(
        layout: FlexboxLayout,
        context: Context,
        type: PokemonProp.Type
    ) {
        layout.addView(TypeCheckBox(context, type = type).apply {
            layoutParams = LayoutParams(WRAP_CONTENT, 30.dp).apply {
                marginStart = 4.dp
                marginEnd = 4.dp
                topMargin = 4.dp
                bottomMargin = 4.dp
            }

            textAlignment = TEXT_ALIGNMENT_CENTER
            setOnClickListener { checkBox ->
                val isChecked = (checkBox as AppCompatCheckBox).isChecked
                updateTypes(isChecked, type)
            }


//            background.setTint(type.toColor(context))
            checkBoxList.add(this)
        })
    }

    private fun updateTypes(isChecked: Boolean, type: PokemonProp.Type) {
        if (isChecked) {
            if (typeQueue.size >= 2) {
                checkBoxList.first {
                    it.tag == typeQueue.first.name
                }.isChecked = false
                typeQueue.removeFirst()
            }
            typeQueue.add(type)
        } else {
            typeQueue.remove(type)
        }
        Timber.i(typeQueue.joinToString())
        onSelectedChange?.invoke(typeQueue)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        flexboxLayout.autoMeasure()
        setMeasuredDimension(measuredWidth, flexboxLayout.measuredHeight + 48.dp)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        flexboxLayout.layout(0.dp, 48.dp)
    }
}