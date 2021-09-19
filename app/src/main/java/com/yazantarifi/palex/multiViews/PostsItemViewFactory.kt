package com.yazantarifi.palex.multiViews

import com.yazantarifi.palex.adapter.factory.PalexItemViewsFactory
import com.yazantarifi.palex.multiViews.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.Post
import com.yazantarifi.palex.multiViews.data.PostViewHolder
import com.yazantarifi.palex.multiViews.views.PostImageItemView
import com.yazantarifi.palex.multiViews.views.PostImagesItemView
import com.yazantarifi.palex.multiViews.views.PostTextItemView

class PostsItemViewFactory: PalexItemViewsFactory<Post, PostViewHolder>() {
    
    override fun getSupportedViewTypes(): ArrayList<PalexItemView<Post, PostViewHolder>> {
        return ArrayList<PalexItemView<Post, PostViewHolder>>().apply {
            this.add(PostTextItemView())
            this.add(PostImagesItemView())
            this.add(PostImageItemView())
        }
    }

}
