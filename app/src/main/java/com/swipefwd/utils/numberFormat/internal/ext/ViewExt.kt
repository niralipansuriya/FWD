package com.swipefwd.utils.numberFormat.internal.ext

import android.view.View
import android.widget.EditText

internal fun View.showIf(statement: Boolean) {
    visibility = if (statement) View.VISIBLE else View.GONE
}
