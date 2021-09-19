package com.yazantarifi.palex.adapter.impl

import com.yazantarifi.palex.adapter.PalexItemView
import com.yazantarifi.palex.adapter.PalexViewHolder

interface PalexAdapterImplementation<Item, ViewHolder: PalexViewHolder> {

    fun getExtraCountItems(): Int

    fun addItems(items: List<Item>?)

    fun replaceItems(items: List<Item>?)

    fun getDefaultItemViewType(): Int

    fun addItemViewType(item: PalexItemView<Item, ViewHolder>)

}