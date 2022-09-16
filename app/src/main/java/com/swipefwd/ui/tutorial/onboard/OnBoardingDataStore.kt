package com.swipefwd.ui.tutorial.onboard

import com.swipefwd.R

object OnBoardingDataStore {


    var lstOfOnBoardingItems = mutableListOf<OnBoardingItem>()

    fun create(): List<OnBoardingItem> {
        if (lstOfOnBoardingItems.isNotEmpty()) return lstOfOnBoardingItems

        lstOfOnBoardingItems.add(
            OnBoardingItem(
                R.raw.tutorial_onboarding_one,
                R.string.on_boarding_title_1,
                R.string.on_boarding_desc_1
            )
        )
        lstOfOnBoardingItems.add(
            OnBoardingItem(
                R.raw.tutorial_onboarding_two,
                R.string.on_boarding_title_2,
                R.string.on_boarding_desc_2
            )
        )
        lstOfOnBoardingItems.add(
            OnBoardingItem(
                R.raw.tutorial_onboarding_three,
                R.string.on_boarding_title_3,
                R.string.on_boarding_desc_3
            )
        )
        lstOfOnBoardingItems.add(
            OnBoardingItem(
                R.raw.tutorial_onboarding_four,
                R.string.on_boarding_title_4,
                R.string.on_boarding_desc_4
            )
        )
        return lstOfOnBoardingItems
    }
}


data class OnBoardingItem(val jsonRes: Int, val title: Int, val desc: Int)