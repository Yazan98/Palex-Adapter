package com.yazantarifi.palex.multiViews

import com.yazantarifi.palex.adapter.factory.PalexItemViewsFactory
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.multiViews.views.PostImageItemView
import com.yazantarifi.palex.multiViews.views.GoogleAdsItemView
import com.yazantarifi.palex.multiViews.views.PostTextItemView

class PostsItemViewFactory: PalexItemViewsFactory<PalexItem>() {
    override fun getSupportedViewTypes(): ArrayList<PalexItemView<PalexItem, *>> {
        return ArrayList<PalexItemView<PalexItem, *>>().apply {
            this.add(PostTextItemView())
            this.add(GoogleAdsItemView())
            this.add(PostImageItemView())
        }
    }
}
