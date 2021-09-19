package com.yazantarifi.palex.multiViews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.PalexAdapter
import com.yazantarifi.palex.adapter.PalexRecyclerViewInit
import com.yazantarifi.palex.multiViews.data.Post
import com.yazantarifi.palex.multiViews.data.PostViewHolder
import com.yazantarifi.palex.adapter.listeners.PalexAdapterErrorListener
import com.yazantarifi.palex.adapter.listeners.PalexAdapterPaginationCallback
import kotlinx.android.synthetic.main.fragment_multi_views_example.*
import java.lang.Exception

class MultiViewFragmentExample: Fragment(R.layout.fragment_multi_views_example), PalexAdapterErrorListener {

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
            PalexRecyclerViewInit.initVerticalView(requireContext(), this, getPostsAdapter())
        }
    }

    private fun getPostsAdapter(): PalexAdapter<Post, PostViewHolder> {
        return PalexAdapter<Post, PostViewHolder>(getPostsItems(), requireContext(), pool).apply {
            this.addErrorsCallback(this@MultiViewFragmentExample)
            this.addPaginationStatus(true, 10, paginationCallback)
            this.setViewTypesFactory(PostsItemViewFactory())
            this.setHasStableIds(true)
        }
    }

    private fun getPostsItems(): ArrayList<Post> {
        return ArrayList<Post>().apply {

        }
    }

    private val paginationCallback: PalexAdapterPaginationCallback = object : PalexAdapterPaginationCallback {
        override fun onNextPageRequest() {

        }
    }

    override fun onErrorAttached(exception: Exception) {
        println("III :: onError : ${exception.message}")
    }

}