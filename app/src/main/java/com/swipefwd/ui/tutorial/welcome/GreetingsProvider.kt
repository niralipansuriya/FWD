package com.swipefwd.ui.tutorial.welcome

import com.swipefwd.R

internal object GreetingsProvider {

    var lstOfGreets = mutableListOf<Greet>()

    fun create(): List<Greet> {
        if (lstOfGreets.isNotEmpty()) return lstOfGreets

        lstOfGreets.add(Greet(R.drawable.avo_zip, R.string.pager_title_1, R.string.pager_desc_1))
        lstOfGreets.add(Greet(R.drawable.avo_zip, R.string.pager_title_2, R.string.pager_desc_2))
        lstOfGreets.add(Greet(R.drawable.avo_zip, R.string.pager_title_3, R.string.pager_desc_3))
        lstOfGreets.add(Greet(R.drawable.avo_zip, R.string.pager_title_4, R.string.pager_desc_4))
        return lstOfGreets

    }
}


data class Greet(val resource: Int, val title: Int, val desc: Int)