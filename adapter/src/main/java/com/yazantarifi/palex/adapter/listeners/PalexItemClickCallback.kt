package com.yazantarifi.palex.adapter.listeners

abstract class PalexItemClickCallback<Item> {

    fun onItemClicked(item: Item, position: Int, view: Int) = Unit

    fun onItemLongClicked(item: Item, position: Int, view: Int) = Unit

}
