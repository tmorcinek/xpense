package com.morcinek.xpense.common.utils

import android.widget.ListView

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun <T> ListView.forEachItemIndexed(function: ListView.(Int, T) -> Unit) {
    for (position in 0..count - 1) {
        function(position, getItemAtPosition(position) as T)
    }
}

fun <T> ListView.getCheckedItems(): List<T> {
    val checkedItems = arrayListOf<T>()
    forEachItemIndexed { position, item: T ->
        if (checkedItemPositions.get(position)) {
            checkedItems.add(item)
        }
    }
    return checkedItems
}