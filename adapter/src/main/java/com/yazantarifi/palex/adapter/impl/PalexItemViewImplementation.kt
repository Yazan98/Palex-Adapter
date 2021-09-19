package com.yazantarifi.palex.adapter.impl

import android.content.Context
import android.view.LayoutInflater

interface PalexItemViewImplementation<Item, ViewHolder> {

    fun onBindViewItem(item: Item, position: Int, viewHolder: ViewHolder, context: Context)

    fun onBindChildViewsClickable()

    fun onBindViewHolder(inflater: LayoutInflater): ViewHolder

    fun getLayoutResource(): Int

    fun getViewType(): Int

}
