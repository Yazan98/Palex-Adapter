package com.yazantarifi.palex.adapter

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object PalexRecyclerViewInit {

    @JvmStatic
    fun initHorizontalView(context: Context, recyclerView: RecyclerView?, adapter: RecyclerView.Adapter<*>, isReverse: Boolean = false) {
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, isReverse)
            it.adapter = adapter
        }
    }

    @JvmStatic
    fun initVerticalView(context: Context, recyclerView: RecyclerView?, adapter: RecyclerView.Adapter<*>, isReverse: Boolean = false) {
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, isReverse)
            it.adapter = adapter
        }
    }

    @JvmStatic
    fun initGridView(context: Context, recyclerView: RecyclerView?, adapter: RecyclerView.Adapter<*>, spanSize: Int) {
        recyclerView?.let {
            it.layoutManager = GridLayoutManager(context, spanSize)
            it.adapter = adapter
        }
    }

    @JvmStatic
    fun getAdapter(recyclerView: RecyclerView?): PalexAdapter<*, *>? {
        return recyclerView?.adapter as? PalexAdapter<*, *>
    }

}
