package com.yazantarifi.palex.singleItem

import android.annotation.SuppressLint
import android.content.Context
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.PalexSingleItemAdapter

class ItemExampleAdapter constructor(
    items: ArrayList<ItemExample>,
    context: Context
): PalexSingleItemAdapter<ItemExample, ItemExampleViewHolder>(context, R.layout.row_single_item, items) {

    @SuppressLint("SetTextI18n")
    override fun onBindItem(
        item: ItemExample,
        context: Context,
        position: Int,
        viewHolder: ItemExampleViewHolder
    ) {
        viewHolder.description?.text = item.description
        viewHolder.postText?.text = item.title
        viewHolder.position?.text = "Position : $position"
    }

    override fun getViewHolder(context: Context): ItemExampleViewHolder {
        return ItemExampleViewHolder(getLayoutInstance(context))
    }

}
