package com.morcinek.xpense.common.utils

import android.widget.ListView

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun <T> ListView.forEachItemIndexed(function: ListView.(Int, T) -> Unit) {
    for (index in 0..count - 1) {
        function(index, getItemAtPosition(index) as T)
    }
}

fun <T> ListView.getCheckedItems(): List<T> {
    val checkedItems = arrayListOf<T>()
    forEachItemIndexed { index, item: T ->
        if (isItemChecked(index)) {
            checkedItems.add(item)
        }
    }
    return checkedItems
}

fun ListView.hasCheckedItems() = checkedItemCount > 0