package com.yazantarifi.palex.adapter.impl

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.data.PalexSingleItem
import com.yazantarifi.palex.adapter.listeners.PalexItemClickCallback

interface PalexSingleItemAdapterImplementation<Item: PalexSingleItem, ViewHolder: RecyclerView.ViewHolder> {

    fun onBindItem(item: Item, context: Context, position: Int, viewHolder: ViewHolder)

    fun onBindClickableItems(viewHolder: ViewHolder, position: Int)

    fun onChildViewsClickableItemsBinding(viewHolder: ViewHolder, position: Int)

    fun addChildClickableViewIds(childClickableIds: ArrayList<Int>)

    fun addClickListener(callback: PalexItemClickCallback<Item>)

    fun getViewHolder(context: Context): ViewHolder

    fun getLayoutInstance(context: Context): View

}