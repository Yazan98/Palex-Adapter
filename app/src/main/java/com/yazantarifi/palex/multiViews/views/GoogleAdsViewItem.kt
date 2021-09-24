package com.yazantarifi.palex.multiViews.views

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.Post
import kotlinx.android.synthetic.main.row_image_post.view.*

class GoogleAdsViewItem: PalexItemView<PalexItem, GoogleAdsViewItem.ViewHolder>() {
    override fun onBindViewItem(
        item: PalexItem,
        position: Int,
        viewHolder: ViewHolder,
        context: Context,
        pool: RecyclerView.RecycledViewPool?
    ) {
        (item as? Post)?.let {
            viewHolder.title?.text = it.title
            viewHolder.image?.let { it1 ->
                Picasso.get().load(it.singleImage).into(it1)
            }
        }
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
        val title: TextView? = view.title
        val image: ImageView? = view.loadPostImage
    }
}