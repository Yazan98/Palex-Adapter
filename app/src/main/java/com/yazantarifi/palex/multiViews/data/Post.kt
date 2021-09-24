package com.yazantarifi.palex.multiViews.data

import com.yazantarifi.palex.adapter.data.PalexItem

data class Post(
    var id: Long = 0,
    var title: String = "",
    var description: String = "",
    var createdAt: Long,
    var type: Int,
    var profileImage: String,
    var ownerName: String,
    var images: List<String>,
    var singleImage: String,
    var isNative: Boolean = false
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
