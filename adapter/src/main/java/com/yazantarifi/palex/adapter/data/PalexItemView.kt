package com.yazantarifi.palex.adapter.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.R
import com.yazantarifi.palex.adapter.impl.PalexItemViewImplementation
import java.lang.Exception

abstract class PalexItemView<Item, ViewHolder: RecyclerView.ViewHolder>: PalexItemViewImplementation<Item, ViewHolder> {

    /**
     * Calling This Method when you want to Bind View Item Layout
     * Using Normal LayoutInflater to inflate the Target Layout
     */
    override fun getLayout(context: Context): View {
        return LayoutInflater.from(context).inflate(getLayoutResource(), null, false)
    }

    /**
     * Add Ripple Click Effect on Each Target View
     * If you want to Add Click Effect on Each Item in Adapter
     * Call This Method - Not Safe Call , To Double Check Call this Method in Try, Catch
     */
    @SuppressLint("Recycle")
    override fun addClickEffectItem(view: View?) {
        view?.let {
            val attrs = intArrayOf(R.attr.selectableItemBackground)
            val typedArray: TypedArray = view.context.obtainStyledAttributes(attrs)
            val backgroundResource = typedArray.getResourceId(0, 0)
            view.setBackgroundResource(backgroundResource)
        }
    }

}
