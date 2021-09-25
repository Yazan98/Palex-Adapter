# Palex-Adapter

## Description
Simple Android Library Built to Make RecyclerView Adapters Easly to Build with Defined Multiple Types of Adapters

This Library built with Pre Loaded Adapters and Methods that Commonly Used in one library

## Adapters
All Supported Types in Palex Adapter
1. Multiple Views Type
2. Single View Type
3. Single Item Stable Id Type
4. Multiple Stable Id View Type

## Info

This Library Built with Kotlin Languages Based on RecyclerView Library (1.2.1)
 And Supported By View LayoutInflater To Bind ViewHolders

1. Single Item Adapter (2 Types)
You Need to Build Class To Override The Required Methods in Adapter to Bind the Views with Data

2. Multiple Item Adapter (2 Types)
You Don't Need to Create Class to Override Anything Based on Adapter But you need to Make ItemView for Each Item
To Bind the Views with Data

## Installation
```
dependency {
    implementation "com.yazatarifi.palex-android:1.0.1"
}
```

## Example
1. Single Item Adapter

> Create Item

```
data class ItemExample(
    val title: String,
    val description: String
): PalexSingleItem
```

> Implement Adapter Class To Override The Required Methods

```
class ItemExampleAdapter constructor(
    items: ArrayList<ItemExample>,
    context: Context
): PalexSingleItemAdapter<ItemExample, ItemExampleViewHolder>(context, R.layout.row_single_item, items) {

    override fun onBindItem(
        item: ItemExample,
        context: Context,
        position: Int,
        viewHolder: ItemExampleViewHolder
    ) {
        viewHolder.description?.text = item.description
        viewHolder.postText?.text = item.title
        viewHolder.position?.text = "Position : $position"

        // Used to Add Ripple Click Effect on Each Item
        addClickEffectItem(viewHolder.itemView)
    }

    override fun getViewHolder(context: Context): ItemExampleViewHolder {
        return ItemExampleViewHolder(getLayoutInstance(context))
    }

}
```

> Create ViewHolder to Adapter

```
class ItemExampleViewHolder constructor(view: View): RecyclerView.ViewHolder(view) {
    val postText: TextView? = view.postText
    val description: TextView? = view.description
    val position: TextView? = view.position
}
```

> Create Adapter Instance

```
private val adapterInstance by lazy {
        ItemExampleAdapter(getItems(), this@SingleItemExampleScreen)
}
```

> Add Adapter To RecyclerView

```
singleItemRecyclerView?.apply {
    PalexRecyclerViewInit.initVerticalView(this@SingleItemExampleScreen, this, adapterInstance)
    adapterInstance.addRecyclerViewInstance(this@apply)
    adapterInstance.addPaginationListener(object: PalexAdapterPaginationCallback {
         override fun onNextPageRequest() {
             adapterInstance.addItems(getItems())
         }
    })
}
```

## Multi Items Example
2. Multiple Items Views Adapter Example

> You Need to Create The Item That Will Start Binding in Each RecyclerView Item

```
data class Post(
    var id: Long = 0,
    var title: String = "",
    var description: String = ""
): PalexItem {

    companion object {
        const val TEXT = 1
        const val GOOGLE_ADS = 2
        const val SINGLE_IMAGE = 3
    }

    override fun getItemViewType(): Int {
        return type
    }

}
```

> Create Base ViewHolder

```
open class PostViewHolder constructor(view: View): RecyclerView.ViewHolder(view)
```

> Now Create ItemView for Each View Type in Posts

1. First Supported ItemView
```
class PostImageItemView: PalexItemView<PalexItem, PostImageItemView.ViewHolder>() {
    override fun onBindViewItem(
        item: PalexItem,
        position: Int,
        viewHolder: ViewHolder,
        context: Context,
        pool: RecyclerView.RecycledViewPool?
    ) {
        (item as? Post)?.let {
            viewHolder.title?.text = it.title
            viewHolder.image?.let { it1 ->
                Picasso.get().load(it.singleImage).into(it1)
            }
        }
    }

    override fun onBindViewHolder(context: Context): ViewHolder {
        return ViewHolder(getLayout(context))
    }

    override fun getLayoutResource(): Int {
        return R.layout.row_image_post
    }

    override fun getViewType(): Int {
        return Post.SINGLE_IMAGE
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView? = view.title
        val image: ImageView? = view.loadPostImage
    }
}
```

2. Second Supported ItemView

```

class PostTextItemView: PalexItemView<PalexItem, PostTextItemView.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val description: TextView? = view.description
        val postText: TextView? = view.postText
    }

    override fun onBindViewItem(item: PalexItem, position: Int, viewHolder: ViewHolder, context: Context, pool: RecyclerView.RecycledViewPool?) {
        (item as? Post?)?.let {
            viewHolder.description?.text = it.description
            viewHolder.postText?.text = it.title
        }
    }

    override fun onBindViewHolder(context: Context): ViewHolder {
        return ViewHolder(getLayout(context))
    }

    override fun getLayoutResource(): Int {
        return R.layout.row_post_text
    }

    override fun getViewType(): Int {
        return Post.TEXT
    }

}
```

3. Third Supported ItemView

```
class GoogleAdsItemView: PalexItemView<PalexItem, GoogleAdsItemView.ViewHolder>() {

    override fun onBindViewItem(
        item: PalexItem,
        position: Int,
        viewHolder: ViewHolder,
        context: Context,
        pool: RecyclerView.RecycledViewPool?
    ) {
        (item as? Post)?.let {
            viewHolder.adManagerAdView?.let { it1 -> loadBannerItem(it1, it) }
        }
    }

    private fun loadBannerItem(itemView: AdManagerAdView, item: Post) {
        itemView.setAdSizes(AdSize.BANNER)
        item.adView = itemView
    }

    override fun onBindViewHolder(context: Context): ViewHolder {
        return ViewHolder(getLayout(context))
    }

    override fun getLayoutResource(): Int {
        return R.layout.row_ads_item
    }

    override fun getViewType(): Int {
        return Post.GOOGLE_ADS
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val adManagerAdView: AdManagerAdView? = view.adManagerAdView
    }
}
```

> Create ItemView Factory To Add All of them To Adapter

```
class PostsItemViewFactory: PalexItemViewsFactory<PalexItem>() {
    override fun getSupportedViewTypes(): ArrayList<PalexItemView<PalexItem, *>> {
        return ArrayList<PalexItemView<PalexItem, *>>().apply {
            this.add(PostTextItemView())
            this.add(GoogleAdsItemView())
            this.add(PostImageItemView())
        }
    }
}
```

> Create Adapter Instance and Add All of Information to Adapter

```
adapterInstance = PalexAdapter<PalexItem, PostViewHolder>(getPostsItems(), requireContext(), pool).apply {
    this.addErrorsCallback(this@MultiViewFragmentExample)
    this.setViewTypesFactory(PostsItemViewFactory())
    this.setHasStableIds(true)

    adapterInstance?.addRecyclerViewInstance(recyclerView)
    adapterInstance?.addPaginationListener(object: PalexAdapterPaginationCallback {
        override fun onNextPageRequest() {
              println("III on New Page Request")
              adapterInstance?.addItems(getItems())
        }
    })
}
```

> Assign Adapter To RecyclerView

```
multiViewsRecyclerView?.apply {
    this.setRecycledViewPool(pool)
    getPostsAdapter(this)?.let {
          PalexRecyclerViewInit.initVerticalView(requireContext(), this, it)
    }
}
```

## Supported Features
1. Pagination Endless of RecyclerView
2. Add Ripple Effect On Click
3. Add Clickable Ids for All Items Via Click Factory
4. Replace, Add Items to Adapter
5. Vertical Divider
6. Error Listener To Listen to All Errors in One Callback
7. Animated Remove Item in Adapter, ItemView

## Next Features
1. Support Data Binding ItemViews
2. Support View Binding ItemViews
3. Generate ViewHolders In ItemViews
4. Support Loading Item, Page Size for Pagination

## License

Copyright (C) 2021 Palex Adapter is An Open Source Library (Licensed under the MIT License)

