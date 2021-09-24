package com.yazantarifi.palex.adapter.impl

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.factory.PalexClickableViewsFactory
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.adapter.factory.PalexItemViewsFactory
import com.yazantarifi.palex.adapter.listeners.PalexAdapterErrorListener
import com.yazantarifi.palex.adapter.listeners.PalexAdapterPaginationCallback
import com.yazantarifi.palex.adapter.listeners.PalexItemClickCallback
import com.yazantarifi.palex.adapter.listeners.PalexRemoveListener

interface PalexAdapterImplementation<Item: PalexItem, ViewHolder: RecyclerView.ViewHolder> {

    fun getExtraCountItems(): Int

    fun addItems(items: List<Item>?)

    fun replaceItems(items: List<Item>?)

    fun addItemViewType(item: PalexItemView<Item, ViewHolder>)

    fun addItemClickListener(callback: PalexItemClickCallback<Item>)

    fun getItemByPosition(position: Int): Item?

    fun addErrorsCallback(callback: PalexAdapterErrorListener)

    fun addRemoveCallback(callback: PalexRemoveListener<Item>)

    fun removeItem(position: Int)

    fun removeItem(position: Int, isAnimationEnabled: Boolean, animationDuration: Long, targetView: View?)

    fun setViewClickListener(view: Int)

    fun setChildViewClickListener(view: Int)

    fun setClickableViewsFactory(factory: PalexClickableViewsFactory)

    fun setViewTypesFactory(factory: PalexItemViewsFactory<Item>)

    fun getItemByViewType(viewType: Int): Item?

    fun getPositionByItemType(itemType: Int, isAsc: Boolean): Int

    fun bindClickableViews(itemView: View, item: Item, position: Int)

    fun getItems(): ArrayList<Item>

    fun destroy()

}
