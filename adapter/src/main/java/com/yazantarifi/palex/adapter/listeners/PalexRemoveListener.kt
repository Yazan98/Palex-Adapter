package com.yazantarifi.palex.adapter.listeners

interface PalexRemoveListener<Item> {

    fun onItemRemoved(item: Item, position: Int)

}