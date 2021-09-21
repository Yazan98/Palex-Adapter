package com.yazantarifi.palex.adapter.impl

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.data.PalexSingleItem

interface PalexSingleItemAdapterImplementation<Item: PalexSingleItem, ViewHolder: RecyclerView.ViewHolder> {

    fun onBindItem(item: Item, context: Context, position: Int, viewHolder: ViewHolder)

    fun getViewHolder(context: Context): ViewHolder

    fun getLayoutInstance(context: Context): View

}