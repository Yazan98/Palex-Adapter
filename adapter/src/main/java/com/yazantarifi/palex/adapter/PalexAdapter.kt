package com.yazantarifi.palex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.impl.PalexAdapterImplementation
import java.lang.Exception

abstract class PalexAdapter<Item: PalexItem, ViewHolder: PalexViewHolder> constructor(
    private val items: ArrayList<Item>? = null,
    private val context: Context
) : RecyclerView.Adapter<ViewHolder>(), PalexAdapterImplementation<Item, ViewHolder> {

    companion object {
        private const val EXTRA_COUNT_ITEMS = 0
    }

    private val currentViewItems: ArrayList<PalexItemView<Item, ViewHolder>> by lazy { ArrayList() }
    private val currentItems: ArrayList<Item> by lazy {
        ArrayList(items ?: ArrayList())
    }

    /**
     * This Method Used to Add Supported ViewTypes to Current Adapter
     * Each ViewType has it's ViewHolder and Item, Current Layout
     * This Method Will Send All ViewTypes to Supported ViewHolders
     *
     * And onBindView will Bind Each Item With the Current Item
     */
    override fun addItemViewType(item: PalexItemView<Item, ViewHolder>) {
        currentViewItems.add(item)
    }

    /**
     * This Method will Add Items to Current Items Used To Add More Items
     * This Method Will Notify Data Set Changed in Adapter
     */
    override fun addItems(items: List<Item>?) {
        items?.let {
            this.currentItems.addAll(it)
            notifyDataSetChanged()
        }
    }

    /**
     * This Method Will Remove The Prev Items in Adapter and Refresh All Items
     * In Adapter to New Items and Notify Data Set Changed in Adapter
     */
    override fun replaceItems(items: List<Item>?) {
        items?.let {
            this.currentItems.clear()
            this.currentItems.addAll(it)
            notifyDataSetChanged()
        }
    }

    /**
     * Create ViewHolder By Resource Layout on Each Item View
     * This Method Will Loop on Supported Items to Find Item by Specific Type
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var currentItem: PalexItemView<Item, ViewHolder>? = null
        for (value in this.currentViewItems) {
            if (value.getViewType() == viewType) {
                currentItem = value
            }
        }

        return currentItem?.onBindViewHolder(parent.context) as ViewHolder
    }

    /**
     * This Method Will Loop on all supported ViewType
     * and Bind each View in Adapter in Their own Bind Method
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = currentItems[position]
        for (i in currentViewItems) {
            if (i.getViewType() == currentItem.getItemViewType()) {
                i.onBindViewItem(currentItem, position, holder, context)
            }
        }
    }

    /**
     * This Method Will Decide Which View has The Specific ViewType for Each ItemView
     * If The ItemView Found by Position will Return it else will Return Default ItemView Type
     */
    override fun getItemViewType(position: Int): Int {
        try {
            val currentItem = currentItems[position] as? PalexItemView<Item, ViewHolder> ?: return super.getItemViewType(position)
            return currentItem.getViewType()
        } catch (ex: Exception) {
            return super.getItemViewType(position)
        }
    }

    /**
     * Get The Total RecyclerView Adapter Items Count
     * Needed To Know Everything about Recycling in Adapter
     */
    override fun getItemCount(): Int {
        return currentItems.size + getExtraCountItems()
    }

    /**
     * This Method Used When You want To Recycle More Items Than Data
     * For Example if you want to Add Items That is Not Related To Primary List Use This Method
     * Return Number Of Extra Items to Add Them to Primary Count of Data
     *
     * The Usage of this Method is That You want to Add Static Layouts without Adding Them to Primary List
     * and Filter Them Always you can access them only by Position
     */
    override fun getExtraCountItems(): Int {
        return EXTRA_COUNT_ITEMS
    }

}