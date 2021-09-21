package com.yazantarifi.palex.adapter.impl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface PalexItemViewImplementation<Item, ViewHolder> {

    fun onBindViewItem(item: Item, position: Int, viewHolder: ViewHolder, context: Context, pool: RecyclerView.RecycledViewPool? = null)

    fun onBindViewHolder(context: Context): ViewHolder

    fun addClickEffectItem(view: View?)

    fun getLayout(context: Context): View

    fun getLayoutResource(): Int

    fun getViewType(): Int

}
