package com.yazantarifi.palex.adapter.factory

/**
 * If Your Adapter has Static Clickable Views You can Attach This Factory
 * In Palex Adapter and this Will Fill Everything in Adapter
 * To Bind Clickable Views Better Than Add them in Fragment or Activity
 * Your Factory Will Be Implemented in Class Alone and Bind this Factory to PalexAdapter
 * Using This Method (adapter.setClickableViewsFactory(Instance))
 */
abstract class PalexClickableViewsFactory {

    abstract fun getParentClickableViews(): ArrayList<Int>

    abstract fun getChildsClickableViews(): ArrayList<Int>

}
