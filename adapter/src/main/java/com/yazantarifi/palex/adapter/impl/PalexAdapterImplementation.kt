package com.yazantarifi.palex.adapter.impl

import android.view.View
import com.yazantarifi.palex.adapter.data.PalexClickableViewsFactory
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.adapter.data.PalexViewHolder
import com.yazantarifi.palex.adapter.listeners.PalexAdapterErrorListener
import com.yazantarifi.palex.adapter.listeners.PalexAdapterPaginationCallback
import com.yazantarifi.palex.adapter.listeners.PalexItemClickCallback

interface PalexAdapterImplementation<Item: PalexItem, ViewHolder: PalexViewHolder> {

    fun getExtraCountItems(): Int

    fun addItems(items: List<Item>?)

    fun replaceItems(items: List<Item>?)

    fun addItemViewType(item: PalexItemView<Item, ViewHolder>)

    fun addItemClickListener(callback: PalexItemClickCallback<Item>)

    fun addErrorsCallback(callback: PalexAdapterErrorListener)

    fun setViewClickListener(view: Int)

    fun setChildViewClickListener(view: Int)

    fun setClickableViewsFactory(factory: PalexClickableViewsFactory)

    fun bindClickableViews(itemView: View, item: Item, position: Int)

    fun addPaginationStatus(isEnabled: Boolean, pageSize: Int, callback: PalexAdapterPaginationCallback)

    fun changePaginationStatus(isFinished: Boolean, newItems: ArrayList<Item>)

    fun destroy()

}
