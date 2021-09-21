package com.yazantarifi.palex.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.factory.PalexClickableViewsFactory
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.adapter.factory.PalexItemViewsFactory
import com.yazantarifi.palex.adapter.impl.PalexAdapterImplementation
import com.yazantarifi.palex.adapter.listeners.PalexAdapterErrorListener
import com.yazantarifi.palex.adapter.listeners.PalexAdapterPaginationCallback
import com.yazantarifi.palex.adapter.listeners.PalexItemClickCallback
import java.lang.Exception

open class PalexAdapter<Item: PalexItem, ViewHolder: RecyclerView.ViewHolder> constructor(
    private val currentItems: ArrayList<Item> = ArrayList(),
    private val context: Context,
    private val viewPool: RecyclerView.RecycledViewPool? = null
) : RecyclerView.Adapter<ViewHolder>(), PalexAdapterImplementation<Item, ViewHolder> {

    private var paginationPageSize: Int = 0
    private var isPaginationEnabled: Boolean = false
    private var errorsCallback: PalexAdapterErrorListener? = null
    private var paginationCallback: PalexAdapterPaginationCallback? = null
    private var clickCallback: PalexItemClickCallback<Item>? = null
    private val parentClickableViews: ArrayList<Int> by lazy { ArrayList<Int>() }
    private val childsClickableViews: ArrayList<Int> by lazy { ArrayList() }
    private val currentViewItems: ArrayList<PalexItemView<Item, ViewHolder>> by lazy(LazyThreadSafetyMode.NONE) { ArrayList() }
    companion object {
        private const val EXTRA_COUNT_ITEMS = 0
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
     * This Method Used To Attach Click Listener on Parent Item in Each ViewType
     * This Callback will Recive Clicks from All Views in Adapter Just on Parent Views
     *
     * If You Want to Add Click Listener on Child Views You Need To Attach them in Adapter
     * By Calling setChildClickListener(itemView: Int)
     */
    override fun addItemClickListener(callback: PalexItemClickCallback<Item>) {
        this.clickCallback = callback
    }

    /**
     * This Method Used to Add All Clickable Childs Ids to Loop on Them
     * If The Binding Method Found Any Id from These Ids Will Add Click Listener on it
     * and Return the Click in PalexItemClickCallback
     *
     * You can Call this Method Multiple Times to Add A lot of Clickable Views
     */
    override fun setChildViewClickListener(@IdRes view: Int) {
        this.childsClickableViews.add(view)
    }

    /**
     * This method Used to Add Click Listener on Parent Views Only
     * You Need to Call This method Once Adapter Created to Add All Clickable Views
     * and The Click Action will returned in PalexItemClickCallback
     *
     * You can Call this Method Multiple Times to Add A lot of Clickable Views
     */
    override fun setViewClickListener(@IdRes view: Int) {
        this.parentClickableViews.add(view)
    }

    /**
     * If you want To Add Clickable Views to Your Adapter
     * You Need to Add Clickable Views
     *
     * You can Use this Methods if your Views is Dynamic or you want to Set Them in Fragment or Activity
     * 1. setChildViewClickListener
     * 2. setViewClickListener
     *
     * If you have Static Clickable Ids You can Build Your Clickable Views Factory
     * and Add All Ids to This Factory and this Will do the same as set Childs, Parents
     * Also this Factory is Good to Move this Kind of Logic outside Fragment
     */
    override fun setClickableViewsFactory(factory: PalexClickableViewsFactory) {
        if (!factory.getParentClickableViews().isNullOrEmpty()) {
            this.parentClickableViews.addAll(factory.getParentClickableViews())
        }

        if (!factory.getChildsClickableViews().isNullOrEmpty()) {
            this.parentClickableViews.addAll(factory.getChildsClickableViews())
        }
    }

    /**
     * Add All Supported item Views One Time to Adapter
     * and Adapter Will Map All of them in Supported Item Views Internally
     */
    override fun setViewTypesFactory(factory: PalexItemViewsFactory<Item>) {
        factory.getSupportedViewTypes().forEach {
            this.currentViewItems.add(it as PalexItemView<Item, ViewHolder>)
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
     *
     * Here The Clickable Items Binding Will Start to Add Click Listener on Parent Views
     * Then Will Do Mapping to Map All Items and Call onBindViewItem for Each ItemView in Adapter
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val currentItem = currentItems[position]
            bindClickableViews(holder.itemView, currentItem, position)
            for (viewItem in currentViewItems) {
                if (viewItem.getViewType() == currentItem.getItemViewType()) {
                    viewItem.onBindViewItem(currentItem, position, holder, context, viewPool)
                }
            }

            if (isPaginationEnabled && ((position / paginationPageSize) + paginationPageSize) >= paginationPageSize) {
                paginationCallback?.onNextPageRequest()
            }
        } catch (ex: Exception) {
            this.errorsCallback?.onErrorAttached(ex)
        }
    }

    /**
     * This Method Used when You Add Items From Pagination After Requesting New Pages
     * If The Pagination is Finished Will Remove All Pagination Logic
     * If False will Add items To Prev Items In Adapter and Let Adapter Know
     * The Data Was Changed ...
     */
    override fun changePaginationStatus(isFinished: Boolean, newItems: ArrayList<Item>) {
        if (isFinished) {
            this.isPaginationEnabled = false
            this.paginationPageSize = 0
            this.paginationCallback = null
        }

        addItems(newItems)
    }

    /**
     * Add Pagination Info Here to Request New Page And What is The Page Size For Each Request
     * Here you Can Enable or Disable the Pagination in Adapter
     */
    override fun addPaginationStatus(isEnabled: Boolean, pageSize: Int, callback: PalexAdapterPaginationCallback) {
        this.isPaginationEnabled = isEnabled
        this.paginationPageSize = pageSize
    }

    /**
     * Use This Method To Catch Un Expected Errors in Adapter While Binding or Any Other Exception
     * And Do a Fallback Action in Case Adapter Throws Exception
     */
    override fun addErrorsCallback(callback: PalexAdapterErrorListener) {
        this.errorsCallback = callback
    }

    /**
     * This Method Will Bind the Parent Views in Adapter to Add Click Listener on Them
     * and Return the Click Event for Listener and Perform Action Based on Click
     *
     * Child Views Binding will Start in Each ItemView Internally
     */
    override fun bindClickableViews(itemView: View, item: Item, position: Int) {
        if (this.clickCallback == null) {
            return
        }

        try {
            for (i in parentClickableViews) {
                if (i == itemView.id) {
                    itemView.setOnClickListener {
                        this.clickCallback?.onItemClicked(item, position, it.id)
                    }

                    itemView.setOnLongClickListener {
                        this.clickCallback?.onItemLongClicked(item, position, it.id)
                        true
                    }
                }
            }

            for (childItem in childsClickableViews) {
                itemView.findViewById<View>(childItem)?.let {
                    it.setOnClickListener {
                        this.clickCallback?.onItemLongClicked(item, position, it.id)
                    }
                }
            }
        } catch (ex: Exception) {
            this.errorsCallback?.onErrorAttached(ex)
        }
    }

    /**
     * This Method Will Decide Which View has The Specific ViewType for Each ItemView
     * If The ItemView Found by Position will Return it else will Return Default ItemView Type
     */
    override fun getItemViewType(position: Int): Int {
        try {
            val currentItem = currentItems[position]
            return currentItem.getItemViewType()
        } catch (ex: Exception) {
            errorsCallback?.onErrorAttached(ex)
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

    /**
     * It's Important to Call this Method in
     * Activity -> onDestroy
     * Fragment -> onDestroyView
     *
     * To Remove All Listeners, Callbacks
     */
    override fun destroy() {
        this.clickCallback = null
        this.errorsCallback = null
        this.paginationCallback = null
    }

}
