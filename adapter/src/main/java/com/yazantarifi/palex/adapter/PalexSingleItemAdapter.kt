package com.yazantarifi.palex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.data.PalexSingleItem
import com.yazantarifi.palex.adapter.impl.PalexSingleItemAdapterImplementation
import com.yazantarifi.palex.adapter.listeners.PalexAdapterErrorListener
import com.yazantarifi.palex.adapter.listeners.PalexItemClickCallback
import java.lang.Exception

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

    private var errorListener: PalexAdapterErrorListener? = null
    private var clicksCallback: PalexItemClickCallback<Item>? = null
    private val childClickableIds: ArrayList<Int> by lazy {
        ArrayList()
    }

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
     *
     * Bind on Each Focused Items The Clickable Views
     * 1. Parent Clickable Views
     * 2. Childs Ids Clickable Views
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            onBindClickableItems(holder, position)
            onChildViewsClickableItemsBinding(holder, position)
        } catch (ex: Exception) {
            this.errorListener?.onErrorAttached(ex)
        }

        onBindItem(items[position], context, position, holder)
    }

    /**
     * Bind Each Item Parent Layout With Click Listener and Long Click Listener
     * To Return the Clicked Event to Callback Methods
     *
     * You Need to Call addClickListener(Listener) to Activate this Method
     */
    override fun onBindClickableItems(viewHolder: ViewHolder, position: Int) {
        if (this.clicksCallback == null) {
            return
        }

        viewHolder.itemView?.setOnClickListener {
            this.clicksCallback?.onItemClicked(items[position], position, it.id)
        }

        viewHolder.itemView?.setOnLongClickListener {
            this.clicksCallback?.onItemLongClicked(items[position], position, it.id)
            true
        }
    }

    /**
     * This Method Will Bind the Child Clickable Ids from This Method
     * addChildClickableViewIds(ArrayList<Clickable Layout Ids>)
     *
     * this Will Check on All Child Ids in the ArrayList to Add Click Listeners on Each Item
     */
    override fun onChildViewsClickableItemsBinding(viewHolder: ViewHolder, position: Int) {
        if (this.clicksCallback == null || childClickableIds.isEmpty()) {
            return
        }

        for (item in childClickableIds) {
            viewHolder.itemView?.findViewById<View>(item)?.let {
                it.setOnClickListener {
                    this.clicksCallback?.onItemClicked(items[position], position, it.id)
                }

                it.setOnLongClickListener {
                    this.clicksCallback?.onItemLongClicked(items[position], position, it.id)
                    true
                }
            }
        }
    }

    /**
     * Use this Method Only if your Design has Childs Items Clickable
     * If Not Leave this Metho with Empty Array and the Binder Will Skip Checking
     * on Child Items
     */
    override fun addChildClickableViewIds(childClickableIds: ArrayList<Int>) {
        this.childClickableIds.addAll(childClickableIds)
    }

    /**
     * Use this Method When you want to Bind Clicks on Each Item in The List
     * Just attach the Listener and The Callback Will be Called
     * When You Click on Any Item in Adapter
     */
    override fun addClickListener(callback: PalexItemClickCallback<Item>) {
        this.clicksCallback = callback
    }

    /**
     * In getViewHolder Method If you want to Return ViewHolder with Layout Inflater
     * Use this Method to Get Target View by Attached Layout
     */
    override fun getLayoutInstance(context: Context): View {
        return LayoutInflater.from(context).inflate(layoutResource, null, false)
    }

    /**
     * Use this Method when You want to Set More items in The List
     * Then Notify the Adapter that Items has Been Changed
     * Used After Fetching Next Page from Pagination
     */
    override fun addItems(items: ArrayList<Item>) {
        try {
            this.items.addAll(items)
            notifyDataSetChanged()
        } catch (ex: Exception) {
            this.errorListener?.onErrorAttached(ex)
        }
    }

    /**
     * Use this method when you want to Remove The Current Items
     * And Replace all of them with New Items
     * Then Notify The Adapter that All Items Has Been Changed
     */
    override fun replaceItems(items: ArrayList<Item>) {
        try {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        } catch (ex: Exception) {
            this.errorListener?.onErrorAttached(ex)
        }
    }

    /**
     * Use this Method when You Want to Remove Item By Position
     * Just Call this Method With The Target Remvoed Position and This Will Rmeove
     * The Target Item then Notify Adapter that Item is Removed from List
     */
    override fun removeItem(position: Int) {
       try {
           val currentItem = this.items[position]
           this.items.remove(currentItem)
           notifyItemRemoved(position)
       } catch (ex: Exception) {
           this.errorListener?.onErrorAttached(ex)
       }
    }

    /**
     * Use This Method when You want To Catch un Expected Exceptions in Adapter
     * from Any Method and Return To Log Exceptions
     */
    override fun addErrorListener(callback: PalexAdapterErrorListener) {
        this.errorListener = callback
    }

    /**
     * This Method Will return The Size of All Items in Adapter
     * Used for Recycling, Pagination
     */
    override fun getItemCount(): Int {
        return items.size
    }
}