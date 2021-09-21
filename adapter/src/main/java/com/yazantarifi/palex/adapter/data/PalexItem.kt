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
