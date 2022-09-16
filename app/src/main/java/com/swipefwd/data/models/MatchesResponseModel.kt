package com.swipefwd.data.models

data class MatchesResponseModel(
    val data: Data = Data(),
    val response: String = "",
    val status: Boolean = false
) {
    data class Data(
        val matches: List<Int> = ArrayList<Int>()
    )
}

