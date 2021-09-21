package com.yazantarifi.palex.adapter.listeners

import android.view.View

abstract class PalexItemClickCallback<Item> {

    fun onItemClicked(item: Item, position: Int, view: View?) = Unit

    fun onItemLongClicked(item: Item, position: Int, view: View?) = Unit

}
