package com.yazantarifi.palex.multiViews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.PalexAdapter
import com.yazantarifi.palex.adapter.PalexRecyclerViewInit
import com.yazantarifi.palex.multiViews.data.Post
import com.yazantarifi.palex.multiViews.data.PostViewHolder
import com.yazantarifi.palex.adapter.listeners.PalexAdapterErrorListener
import com.yazantarifi.palex.adapter.listeners.PalexAdapterPaginationCallback
import com.yazantarifi.palex.adapter.data.PalexItem
import kotlinx.android.synthetic.main.fragment_multi_views_example.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MultiViewFragmentExample: Fragment(R.layout.fragment_multi_views_example), PalexAdapterErrorListener {

    private var adapterInstance: PalexAdapter<PalexItem, PostViewHolder>? = null
    private val pool: RecyclerView.RecycledViewPool by lazy {
        RecyclerView.RecycledViewPool()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        multiViewsRecyclerView?.apply {
            this.setRecycledViewPool(pool)
            getPostsAdapter()?.let {
                PalexRecyclerViewInit.initVerticalView(requireContext(), this, it)
                lifecycleScope.launch(Dispatchers.Main) {
                    /**
                     * This is Just an Example for Loading Ads in RecyclerView
                     * Don't Do this In Production it's not a good place to Load Ads in Adapter
                     */
                    delay(2000)
                    it.getItems().let {
                        for (i in it) {
                            (i as? Post)?.let {
                                i.adView?.loadAd(AdManagerAdRequest.Builder().build())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getPostsAdapter(): PalexAdapter<PalexItem, PostViewHolder>? {
        if (adapterInstance == null) {
            adapterInstance = PalexAdapter<PalexItem, PostViewHolder>(getPostsItems(), requireContext(), pool).apply {
                this.addErrorsCallback(this@MultiViewFragmentExample)
                this.setViewTypesFactory(PostsItemViewFactory())
                this.setHasStableIds(true)
            }
        }

        return adapterInstance
    }

    private fun getPostsItems(): ArrayList<PalexItem> {
        return ArrayList<PalexItem>().apply {
            add(Post(0, "Title Here", "description Here Should Be Long String", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Random String Should Be Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description New Randim String for Description", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "p into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "p into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "p into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.GOOGLE_ADS, "https://", "Yazan", emptyList(), ""))

            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
            add(Post(0, "Title Here", "description Here", 127382L, Post.SINGLE_IMAGE, "https://", "Yazan", emptyList(), "https://images.ctfassets.net/hrltx12pl8hq/7yQR5uJhwEkRfjwMFJ7bUK/dc52a0913e8ff8b5c276177890eb0129/offset_comp_772626-opt.jpg?fit=fill&w=800&h=300"))
        }
    }

    private val paginationCallback: PalexAdapterPaginationCallback = object : PalexAdapterPaginationCallback {
        override fun onNextPageRequest() {
            println("III :: onNextRequest")
        }
    }

    override fun onErrorAttached(exception: Exception) {
        println("III :: onError : ${exception.message}")
    }

}