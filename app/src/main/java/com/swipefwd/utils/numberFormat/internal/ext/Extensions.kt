package com.swipefwd.utils.numberFormat.internal.ext

import com.swipefwd.utils.numberFormat.internal.model.State

private const val CHAR_PLUS = "+"

internal fun CharSequence?.prependPlus(): String {
    return StringBuilder()
        .append(CHAR_PLUS)
        .append(this)
        .toString()
}

internal fun CharSequence?.startsWithPlus(): Boolean {
    return this?.startsWith(CHAR_PLUS) == true
}

internal fun String?.clearSpaces(): String? {
    return this?.replace("\\s+", "")
}

internal fun <T> State.doIfAttached(block: State.Attached.() -> T): T? {
    if (this is State.Attached) return block.invoke(this)
    return null
}
