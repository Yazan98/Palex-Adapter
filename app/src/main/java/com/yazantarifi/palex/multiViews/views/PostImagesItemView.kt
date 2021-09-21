package com.yazantarifi.palex.multiViews.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.Post
import com.yazantarifi.palex.multiViews.data.PostViewHolder

class PostImagesItemView: PalexItemView<PalexItem, PostImagesItemView.ViewHolder>() {

    override fun onBindViewItem(
        item: PalexItem,
        position: Int,
        viewHolder: ViewHolder,
        context: Context,
        pool: RecyclerView.RecycledViewPool?
    ) {

    }

    override fun onBindViewHolder(context: Context): ViewHolder {
        return ViewHolder(getLayout(context))
    }

    override fun getLayoutResource(): Int {
        return R.layout.row_images
    }

    override fun getViewType(): Int {
        return Post.IMAGES
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}