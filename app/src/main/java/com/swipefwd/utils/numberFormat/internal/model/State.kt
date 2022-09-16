package com.swipefwd.utils.numberFormat.internal.model

import com.swipefwd.utils.numberFormat.api.Country

sealed class State {

    object Ready : State()

    data class Attached(
        val country: Country,
        val pattern: String,
    ) : State()

    data class AttachedWithoutCountry(
        val pattern: String,
    ) : State()
}
