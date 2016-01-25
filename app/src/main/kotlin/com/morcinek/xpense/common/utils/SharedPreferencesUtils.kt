package com.morcinek.xpense.common.utils

import android.content.SharedPreferences

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun SharedPreferences.putString(preferenceKey: String, preferenceValue: String){
    edit().putString(preferenceKey, preferenceValue).apply();
}

fun SharedPreferences.getString(preferenceKey: String): String? {
    return getString(preferenceKey, null)
}
