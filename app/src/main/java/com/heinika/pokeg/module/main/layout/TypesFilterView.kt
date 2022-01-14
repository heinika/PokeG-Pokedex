package com.heinika.pokeg.module.main.layout

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatCheckBox
import com.heinika.pokeg.info.Type

import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.dp
import com.heinika.pokeg.view.TypeCheckBox
import timber.log.Timber
import java.util.*


class TypesFilterView(context: Context) : LinearLayout(context) {

    var onSelectedChange: ((List<Type>) -> Unit)? = null

    init {
        gravity = Gravity.CENTER_HORIZONTAL
    }

    private val typeQueue = LinkedList<Type>()
    private val checkBoxList = arrayListOf<AppCompatCheckBox>()

    private val flexboxLayout = GridLayout(context).apply {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

        columnCount = 5
        Type.values().forEach {
            if (it != Type.UNKNOWN) {
                addTypeCheckBox(this, context, it)
            }
        }

        this@TypesFilterView.addView(this)
    }


    private fun addTypeCheckBox(
        layout: GridLayout,
        context: Context,
        type: Type
    ) {
        layout.addView(TypeCheckBox(context, type = type).apply {
            layoutParams = LayoutParams(52.dp, 30.dp).apply {
                marginStart = 4.dp
                marginEnd = 4.dp
                topMargin = 4.dp
                bottomMargin = 4.dp
                gravity = Gravity.CENTER
            }

            textAlignment = TEXT_ALIGNMENT_CENTER
            setOnClickListener { checkBox ->
                val isChecked = (checkBox as AppCompatCheckBox).isChecked
                updateTypes(isChecked, type)
            }

            checkBoxList.add(this)
        })
    }

    private fun updateTypes(isChecked: Boolean, type: Type) {
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
        setMeasuredDimension(measuredWidth, flexboxLayout.measuredHeight + SystemBar.statusBarHeight)
    }
}