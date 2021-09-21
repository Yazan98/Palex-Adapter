package com.yazantarifi.palex.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemId

/**
 * Use This Adapter When you Want to Insert Multi Views
 * With Unique Id for Each Item in Adapter with stableIds in RecyclerView
 *
 * If you Don't Have Nested RecyclerViews in Your Screen Send viewPool as Null
 * Context is Always Required in All Palex Adapters
 */
open class PalexItemAdapter<Item: PalexItemId, ViewHolder: RecyclerView.ViewHolder> constructor(
    private val currentItems: ArrayList<Item> = ArrayList(),
    private val context: Context,
    private val viewPool: RecyclerView.RecycledViewPool? = null
): PalexAdapter<Item, ViewHolder>(currentItems, context, viewPool) {

    override fun getItemId(position: Int): Long {
        return currentItems[position].getId()
    }

}
