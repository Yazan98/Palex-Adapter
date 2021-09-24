package com.yazantarifi.palex.adapter

import android.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.adapter.factory.PalexClickableViewsFactory
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.adapter.factory.PalexItemViewsFactory
import com.yazantarifi.palex.adapter.impl.PalexAdapterImplementation
import com.yazantarifi.palex.adapter.listeners.PalexAdapterErrorListener
import com.yazantarifi.palex.adapter.listeners.PalexItemClickCallback
import com.yazantarifi.palex.adapter.listeners.PalexRemoveListener
import java.lang.Exception


/**
 * This Adapter is The Multi Views Adapter Used When You Want to Add Multiple View holders
 * And Bind all Of them By ViewType for Each Item in the List
 *
 * Context Param is Required Always
 * ViewPool Required Only if your ViewHolders has RecyclerView to be Nested RecyclerViews
 * Clickable Views Will start Binding only Via Factories or Attaching Click Listener
 */
open class PalexAdapter<Item: PalexItem, ViewHolder: RecyclerView.ViewHolder> constructor(
    private val currentItems: ArrayList<Item> = ArrayList(),
    private val context: Context,
    private val viewPool: RecyclerView.RecycledViewPool? = null
) : RecyclerView.Adapter<ViewHolder>(), PalexAdapterImplementation<Item, ViewHolder> {

    private var removeCallback: PalexRemoveListener<Item>? = null
    private var errorsCallback: PalexAdapterErrorListener? = null
    private var clickCallback: PalexItemClickCallback<Item>? = null
    private val parentClickableViews: ArrayList<Int> by lazy { ArrayList<Int>() }
    private val childsClickableViews: ArrayList<Int> by lazy { ArrayList() }
    private val currentViewItems: HashMap<Int, PalexItemView<Item, ViewHolder>> by lazy(LazyThreadSafetyMode.NONE) { HashMap() }
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
        currentViewItems[item.getViewType()] = item
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
            this.currentViewItems[it.getViewType()] = it as PalexItemView<Item, ViewHolder>
        }
    }

    /**
     * Create ViewHolder By Resource Layout on Each Item View
     * This Method Will Loop on Supported Items to Find Item by Specific Type
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val currentItem: PalexItemView<Item, ViewHolder>? = currentViewItems[viewType]
        if (currentItem == null) {
            errorsCallback?.onErrorAttached(NullPointerException("View Holder Not Found By ViewType : ${viewType} / Make Sure you Set the ViewType Correctly in ItemView Class"))
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
            currentViewItems[currentItem.getItemViewType()]?.let {
                it.onBindViewItem(currentItem, position, holder, context, viewPool)
            }
        } catch (ex: Exception) {
            this.errorsCallback?.onErrorAttached(ex)
        }
    }

    /**
     * Use This Method Only if You Have Unique Item in Adapter
     * and You Want to Access it By ViewType to Return it's information
     */
    override fun getItemByViewType(viewType: Int): Item? {
        var targetItem: Item? = null
        for (item in currentItems) {
            if (item.getItemViewType() == viewType) {
                targetItem = item
            }
        }

        return targetItem
    }

    /**
     * Use this method Only if You wand To Know the Position of the Item
     * based on it's ViewType
     *
     * Param : isAsc to Decide if you want to Find the Position of the Item
     * From Top To Bottom if True
     * From Bottom To Up if False
     *
     * If The Item Not Found the Returned Value Will be RecyclerView.NO_POSITION
     */
    override fun getPositionByItemType(itemType: Int, isAsc: Boolean): Int {
        return try {
            var currentPosition = RecyclerView.NO_POSITION
            val targetList = if (isAsc) currentItems.withIndex() else currentItems.asReversed().withIndex()
            for ((index, value) in targetList) {
                if (value.getItemViewType() == itemType) {
                    currentPosition = index
                }
            }
            currentPosition
        } catch (ex: Exception) {
            this.errorsCallback?.onErrorAttached(ex)
            RecyclerView.NO_POSITION
        }
    }

    /**
     * Use This Callback When you want to Return the Removed Item Event and it's Position
     * This Method Will Be Trigger the Attached Callback via removeItem(Position)
     */
    override fun addRemoveCallback(callback: PalexRemoveListener<Item>) {
        this.removeCallback = callback
    }

    /**
     * Use this Method when You Want to Remove Item By Position
     * Just Call this Method With The Target Remvoed Position and This Will Rmeove
     * The Target Item then Notify Adapter that Item is Removed from List
     *
     * If you want to Get Event Callback about Removing Item
     * Call this Method addRemoveListener to Attach Remove Listener
     */
    override fun removeItem(position: Int) {
        try {
            if (position == RecyclerView.NO_POSITION) {
                return
            }

            val currentItem = this.currentItems[position]
            this.currentItems.remove(currentItem)
            notifyItemRemoved(position)
            this.removeCallback?.onItemRemoved(currentItem, position)
        } catch (ex: Exception) {
            this.errorsCallback?.onErrorAttached(ex)
        }
    }

    /**
     * Use this Method when You want to Remove Item With Animation
     * If you Don't Use Animation Use this Method (removeItem(Position))
     * if You want a Fade Out Animation Call this Method
     */
    override fun removeItem(position: Int, isAnimationEnabled: Boolean, animationDuration: Long, targetView: View?) {
        try {
            if (position == RecyclerView.NO_POSITION) {
                return
            }

            if (isAnimationEnabled) {
                val anim: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
                anim.duration = animationDuration
                targetView?.startAnimation(anim)
            }

            removeItem(position)
        } catch (ex: Exception) {
            this.errorsCallback?.onErrorAttached(ex)
        }
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
                        this.clickCallback?.onItemClicked(item, position, it)
                    }

                    itemView.setOnLongClickListener {
                        this.clickCallback?.onItemLongClicked(item, position, it)
                        true
                    }
                }
            }

            for (childItem in childsClickableViews) {
                itemView.findViewById<View>(childItem)?.let {
                    it.setOnClickListener {
                        this.clickCallback?.onItemLongClicked(item, position, it)
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
        return try {
            val currentItem = currentItems[position]
            currentItem.getItemViewType()
        } catch (ex: Exception) {
            errorsCallback?.onErrorAttached(ex)
            super.getItemViewType(position)
        }
    }

    override fun getItemByPosition(position: Int): Item? {
        return this.currentItems[position]
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
     * Used When anyone trying to access All Items to Loop on Them
     */
    override fun getItems(): ArrayList<Item> {
        return currentItems
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
        this.removeCallback = null
    }

}
