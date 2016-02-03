package com.morcinek.xpense.common.utils

import android.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Fragment.setTitle(resourceId: Int) {
    (activity as AppCompatActivity).supportActionBar.setTitle(resourceId)
}

fun android.support.v4.app.Fragment.setTitle(resourceId: Int) {
    (activity as AppCompatActivity).supportActionBar.setTitle(resourceId)
}
