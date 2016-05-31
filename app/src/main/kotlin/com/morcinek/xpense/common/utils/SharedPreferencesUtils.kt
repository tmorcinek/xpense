package com.morcinek.xpense.common.utils

import android.content.SharedPreferences

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun SharedPreferences.putBoolean(preferenceKey: String, preferenceValue: Boolean) {
    edit().putBoolean(preferenceKey, preferenceValue).commit();
}

fun SharedPreferences.putLong(preferenceKey: String, preferenceValue: Long) {
    edit().putLong(preferenceKey, preferenceValue).commit()
}

fun SharedPreferences.getLong(preferenceKey: String): Long? {
    if (contains(preferenceKey)) {
        return getLong(preferenceKey, 0)
    } else {
        return null
    }
}
