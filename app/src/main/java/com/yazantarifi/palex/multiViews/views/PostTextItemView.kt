package com.yazantarifi.palex.multiViews.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.Post
import com.yazantarifi.palex.multiViews.data.PostViewHolder
import kotlinx.android.synthetic.main.row_post_text.view.*


class PostTextItemView: PalexItemView<PalexItem, PostTextItemView.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val description: TextView? = view.description
        val postText: TextView? = view.postText
    }

    override fun onBindViewItem(item: PalexItem, position: Int, viewHolder: ViewHolder, context: Context, pool: RecyclerView.RecycledViewPool?) {
        (item as? Post?)?.let {
            viewHolder.description?.text = it.description
            viewHolder.postText?.text = it.title
        }
    }

    override fun onBindViewHolder(context: Context): ViewHolder {
        return ViewHolder(getLayout(context))
    }

    override fun getLayoutResource(): Int {
        return R.layout.row_post_text
    }

    override fun getViewType(): Int {
        return Post.TEXT
    }

}
