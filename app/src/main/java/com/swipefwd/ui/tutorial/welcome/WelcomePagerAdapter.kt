package com.swipefwd.ui.tutorial.welcome

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


class WelcomePagerAdapter(
    private val context: Context,
    private val lstOfGreets:  List<Greet>
) : PagerAdapter() {


    override fun getCount(): Int = lstOfGreets.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(`object` as View)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_welcome_pager, container, false)
        view.findViewById<AppCompatTextView>(R.id.txtTitle).text = context.getString(lstOfGreets[position].title)
        view.findViewById<AppCompatTextView>(R.id.txtDesc).text = context.getString(lstOfGreets[position].desc)
        Glide.with(context).load(lstOfGreets[position].resource).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view.findViewById<AppCompatImageView>(R.id.ivInfo))
        container.addView(view)
        return view
    }
}
