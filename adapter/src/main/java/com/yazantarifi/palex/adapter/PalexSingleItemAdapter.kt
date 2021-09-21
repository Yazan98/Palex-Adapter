package com.yazantarifi.palex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.data.PalexSingleItem
import com.yazantarifi.palex.adapter.impl.PalexSingleItemAdapterImplementation

/**
 * This Adapter mainly Built to Bind Single Item Only
 * This Used when You Have Same Item Id and Layout You can Use this Adapter
 *
 * If you want to Use Multi Items Adapter and Views You Can use (PalexAdapter)
 */
abstract class PalexSingleItemAdapter<Item: PalexSingleItem, ViewHolder: RecyclerView.ViewHolder> constructor(
    private val context: Context,
    private val layoutResource: Int,
    private val items: ArrayList<Item>
): RecyclerView.Adapter<ViewHolder>(), PalexSingleItemAdapterImplementation<Item, ViewHolder> {

    /**
     * Create Your Own Instance in Your Adapter to Return Instance of The ViewHolder
     * This Method is Working Like this Because Maybe You want To Use Anything Of Views Binding
     * Not Just Layout Inflater and This Method Like this To Let you Choose
     * Depends on Your List which Type you want to Use
     *
     * The Default Supported Behavior is returning ViewHolder(getLayoutInstance(context))
     * This Will Use LayoutInflater to Inflate the Target Resource Id
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return getViewHolder(parent.context)
    }

    /**
     * Calling onBindItem on Each Item With All Information Needed for Single Item
     * Used Like this To Get Context Always , In Some Cases it's Better To Send Context
     * Directly Rather than Requesting Context from Views ...
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindItem(items[position], context, position, holder)
    }

    /**
     * In getViewHolder Method If you want to Return ViewHolder with Layout Inflater
     * Use this Method to Get Target View by Attached Layout
     */
    override fun getLayoutInstance(context: Context): View {
        return LayoutInflater.from(context).inflate(layoutResource, null, false)
    }

    /**
     * This Method Will return The Size of All Items in Adapter
     * Used for Recycling, Pagination
     */
    override fun getItemCount(): Int {
        return items.size
    }
}