package com.yazantarifi.palex.multiViews.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.R
import com.yazantarifi.palex.multiViews.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.Post
import com.yazantarifi.palex.multiViews.data.PostViewHolder


class PostTextItemView: PalexItemView<Post, PostViewHolder>() {

    override fun onBindViewItem(item: Post, position: Int, viewHolder: PostViewHolder, context: Context, pool: RecyclerView.RecycledViewPool?) {

    }

    override fun onBindViewHolder(inflater: LayoutInflater): PostViewHolder {
        return PostViewHolder(inflater.inflate(getLayoutResource(), null, false))
    }

    override fun getLayoutResource(): Int {
        return R.layout.row_post_text
    }

    override fun getViewType(): Int {
        return Post.TEXT
    }

}
