package com.morcinek.xpense.common.utils.betterpickers

import android.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Fragment.setTitle(resourceId: Int) {
    (activity as AppCompatActivity).supportActionBar.setTitle(resourceId)
}