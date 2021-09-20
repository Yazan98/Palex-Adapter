package com.yazantarifi.palex.multiViews

import com.yazantarifi.palex.adapter.factory.PalexItemViewsFactory
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.PostViewHolder
import com.yazantarifi.palex.multiViews.views.PostImageItemView
import com.yazantarifi.palex.multiViews.views.PostImagesItemView
import com.yazantarifi.palex.multiViews.views.PostTextItemView

class PostsItemViewFactory: PalexItemViewsFactory<PalexItem, PostViewHolder>() {

    override fun getSupportedViewTypes(): ArrayList<PalexItemView<PalexItem, PostViewHolder>> {
        return ArrayList<PalexItemView<PalexItem, PostViewHolder>>().apply {
            this.add(PostTextItemView())
            this.add(PostImagesItemView())
            this.add(PostImageItemView())
        }
    }

}
