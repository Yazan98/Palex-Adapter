package com.yazantarifi.palex.adapter.factory

import com.yazantarifi.palex.multiViews.data.PalexItem
import com.yazantarifi.palex.multiViews.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.PalexViewHolder

/**
 * Use This Class when You Want to Add All Supported Item Views
 * In One Class Then Add it To Adapter and Adapter Will Set All of Them One time
 */
abstract class PalexItemViewsFactory<Item: PalexItem, ViewHolder: PalexViewHolder> {

    abstract fun getSupportedViewTypes(): ArrayList<PalexItemView<Item, ViewHolder>>

}