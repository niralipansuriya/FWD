package com.swipefwd.ui.home.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.swipefwd.R


class RematchPagerAdapter(
    private val context: Context,
    private val images: Array<Int>,
    private val title: Array<String>
) : PagerAdapter() {

    override fun getCount(): Int = title.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(`object` as View)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_rematch_pager, container, false)
        view.findViewById<AppCompatTextView>(R.id.txtHeader).text = title[position]
        Glide.with(context).load(images[position]).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view.findViewById<AppCompatImageView>(R.id.ivPager))
        container.addView(view)
        return view
    }
}