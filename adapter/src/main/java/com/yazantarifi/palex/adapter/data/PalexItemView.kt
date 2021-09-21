package com.yazantarifi.palex.adapter.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.impl.PalexItemViewImplementation

abstract class PalexItemView<Item, ViewHolder: RecyclerView.ViewHolder>: PalexItemViewImplementation<Item, ViewHolder> {

    /**
     * Calling This Method when you want to Bind View Item Layout
     * Using Normal LayoutInflater to inflate the Target Layout
     */
    override fun getLayout(context: Context): View {
        return LayoutInflater.from(context).inflate(getLayoutResource(), null, false)
    }

}
