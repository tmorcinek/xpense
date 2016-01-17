package com.morcinek.xpense.common.utils

import android.graphics.drawable.GradientDrawable
import android.view.View
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

fun View.setDrawableColor(color: Int) {
    (background as GradientDrawable).setColor(color)
}
