package com.swipefwd.ui.tutorial.onboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.swipefwd.R

class OnBoardPagerAdapter(
        private val context: Context,
        private val lstOfOnBoardingItems:  List<OnBoardingItem>
    ) : PagerAdapter() {


        override fun getCount(): Int = lstOfOnBoardingItems.size

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
            container.removeView(`object` as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_onboard_pager, container, false)
            view.findViewById<AppCompatTextView>(R.id.txtTitle).text = context.getString(lstOfOnBoardingItems[position].title)
            view.findViewById<AppCompatTextView>(R.id.txtDesc).text = context.getString(lstOfOnBoardingItems[position].desc)
            val lottieAnimationView=view.findViewById<LottieAnimationView>(R.id.lottieAnimationView)
            lottieAnimationView.setAnimation(lstOfOnBoardingItems[position].jsonRes)
            lottieAnimationView.playAnimation()
            container.addView(view)
            return view
        }
    }