package com.yazantarifi.palex.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.data.PalexSingleItem
import com.yazantarifi.palex.adapter.data.PalexSingleItemId

/**
 * Use This Type Of Adapter Only if You Have Same Item With Unique Ids
 * If You Don't Have Unique Ids Use PalexSingleItemAdapter
 */
abstract class PalexSingleUniqueItemAdapter<Item: PalexSingleItemId, ViewHolder: RecyclerView.ViewHolder> constructor(
    private val context: Context,
    private val layoutResource: Int,
    private val items: ArrayList<Item>
): PalexSingleItemAdapter<Item, ViewHolder>(context, layoutResource, items) {

    override fun getItemId(position: Int): Long {
        return items[position].getId()
    }

}
