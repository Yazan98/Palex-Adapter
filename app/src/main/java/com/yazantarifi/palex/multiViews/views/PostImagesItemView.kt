package com.yazantarifi.palex.multiViews.views

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.multiViews.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.Post
import com.yazantarifi.palex.multiViews.data.PostViewHolder

class PostImagesItemView: PalexItemView<Post, PostViewHolder>() {
    override fun onBindViewItem(
        item: Post,
        position: Int,
        viewHolder: PostViewHolder,
        context: Context,
        pool: RecyclerView.RecycledViewPool?
    ) {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(inflater: LayoutInflater): PostViewHolder {
        TODO("Not yet implemented")
    }

    override fun getLayoutResource(): Int {
        TODO("Not yet implemented")
    }

    override fun getViewType(): Int {
        TODO("Not yet implemented")
    }
}