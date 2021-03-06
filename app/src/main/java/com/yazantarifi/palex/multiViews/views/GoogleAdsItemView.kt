package com.yazantarifi.palex.multiViews.views

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdView
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.data.PalexItem
import com.yazantarifi.palex.adapter.data.PalexItemView
import com.yazantarifi.palex.multiViews.data.Post
import kotlinx.android.synthetic.main.row_ads_item.view.*
import com.google.android.gms.ads.admanager.AdManagerAdRequest


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