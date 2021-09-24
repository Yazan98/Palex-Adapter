package com.yazantarifi.palex.adapter.impl

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.data.PalexSingleItem
import com.yazantarifi.palex.adapter.listeners.PalexAdapterErrorListener
import com.yazantarifi.palex.adapter.listeners.PalexAdapterPaginationCallback
import com.yazantarifi.palex.adapter.listeners.PalexItemClickCallback
import com.yazantarifi.palex.adapter.listeners.PalexRemoveListener

interface PalexSingleItemAdapterImplementation<Item: PalexSingleItem, ViewHolder: RecyclerView.ViewHolder> {

    fun onBindItem(item: Item, context: Context, position: Int, viewHolder: ViewHolder)

    fun onBindClickableItems(viewHolder: ViewHolder, position: Int)

    fun onChildViewsClickableItemsBinding(viewHolder: ViewHolder, position: Int)

    fun addChildClickableViewIds(childClickableIds: ArrayList<Int>)

    fun addClickEffectItem(view: View?)

    fun addItems(items: ArrayList<Item>)

    fun addRecyclerViewInstance(recyclerView: RecyclerView?)

    fun replaceItems(items: ArrayList<Item>)

    fun removeItem(position: Int)

    fun removeItem(position: Int, isAnimationEnabled: Boolean, animationDuration: Long, targetView: View?)

    fun addClickListener(callback: PalexItemClickCallback<Item>)

    fun addErrorListener(callback: PalexAdapterErrorListener)

    fun addRemoveListener(callback: PalexRemoveListener<Item>)

    fun getItems(): ArrayList<Item>

    fun getViewHolder(context: Context): ViewHolder

    fun getLayoutInstance(context: Context): View

    fun addPaginationListener(paginationCallback: PalexAdapterPaginationCallback)

    fun destroy()

}