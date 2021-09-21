package com.yazantarifi.palex.adapter.factory

import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView

/**
 * Use This Class when You Want to Add All Supported Item Views
 * In One Class Then Add it To Adapter and Adapter Will Set All of Them One time
 */
abstract class PalexItemViewsFactory<Item: PalexItem> {

    abstract fun getSupportedViewTypes(): ArrayList<PalexItemView<Item, *>>

}
