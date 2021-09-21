package com.yazantarifi.palex.adapter.data

/**
 * Type Declaration to Declare Items Types in Single Items Adapter
 */
interface PalexSingleItem

/**
 * Multi Items Types to Define The Target ViewType for Each Item
 * To Tell Recycling in Adapter Which ViewHolder Should Be Called
 * for This Specific Item
 */
interface PalexItem {

    fun getItemViewType(): Int

}

/**
 * This Type of Items Used when You Have Unique Id for Each Item in Adapter
 * And This Adapter has Multi Views
 */
interface PalexItemId : PalexItem {

    fun getId(): Long

}

/**
 * Use This Type of Items When you Have Same Item in Adapter
 * and Each Item has Unique Ids
 */
interface PalexSingleItemId : PalexSingleItem {

    fun getId(): Long

}