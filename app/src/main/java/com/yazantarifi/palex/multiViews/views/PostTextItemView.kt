package com.yazantarifi.palex.multiViews.views

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.Post
import com.yazantarifi.palex.multiViews.data.PostViewHolder


class PostTextItemView: PalexItemView<PalexItem, PostViewHolder>() {

    override fun onBindViewItem(item: PalexItem, position: Int, viewHolder: PostViewHolder, context: Context, pool: RecyclerView.RecycledViewPool?) {

    }

    override fun onBindViewHolder(context: Context): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(context).inflate(getLayoutResource(), null, false))
    }

    override fun getLayoutResource(): Int {
        return R.layout.row_post_text
    }

    override fun getViewType(): Int {
        return Post.TEXT
    }

}
