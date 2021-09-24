package com.yazantarifi.palex.singleItem

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_single_item.view.*

class ItemExampleViewHolder constructor(view: View): RecyclerView.ViewHolder(view) {
    val postText: TextView? = view.postText
    val description: TextView? = view.description
    val position: TextView? = view.position
}
