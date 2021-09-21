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
import com.yazantarifi.palex.adapter.data.PalexItem
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

    private fun getPostsAdapter(): PalexAdapter<PalexItem, PostViewHolder> {
        return PalexAdapter<PalexItem, PostViewHolder>(getPostsItems(), requireContext(), pool).apply {
            this.addErrorsCallback(this@MultiViewFragmentExample)
            this.addPaginationStatus(true, 10, paginationCallback)
            this.setViewTypesFactory(PostsItemViewFactory())
            this.setHasStableIds(true)
        }
    }

    private fun getPostsItems(): ArrayList<PalexItem> {
        return ArrayList<PalexItem>().apply {
            add(Post(0, "Title Here", "description Here Should Be Long String", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Random String Should Be Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description New Randim String for Description", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "p into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
            add(Post(0, "Title Here", "description Here", 127382L, Post.TEXT, "https://", "Yazan", emptyList(), ""))
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