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

class PostImageItemView: PalexItemView<PalexItem, PostImageItemView.ViewHolder>() {
    override fun onBindViewItem(
        item: PalexItem,
        position: Int,
        viewHolder: ViewHolder,
        context: Context,
        pool: RecyclerView.RecycledViewPool?
    ) {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(context: Context): ViewHolder {
        return ViewHolder(getLayout(context))
    }

    override fun getLayoutResource(): Int {
        return R.layout.row_image_post
    }

    override fun getViewType(): Int {
        return Post.SINGLE_IMAGE
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }
}