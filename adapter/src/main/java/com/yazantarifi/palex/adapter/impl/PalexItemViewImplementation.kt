package com.yazantarifi.palex.adapter.impl

import android.content.Context

interface PalexItemViewImplementation<Item, ViewHolder> {

    fun onBindViewItem(item: Item, position: Int, viewHolder: ViewHolder, context: Context)

    fun onBindViewHolder(context: Context): ViewHolder

    fun getLayoutResource(): Int

    fun getViewType(): Int

}
