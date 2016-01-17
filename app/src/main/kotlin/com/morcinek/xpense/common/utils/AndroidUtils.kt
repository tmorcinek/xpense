package com.morcinek.xpense.common.utils

import android.view.Window
import android.view.WindowManager

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Window.showSoftInput() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}

fun Window.hideSoftInput() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
}
