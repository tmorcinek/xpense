package com.morcinek.xpense.common.utils

import android.support.design.widget.AppBarLayout
import android.support.v7.widget.Toolbar

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Toolbar.enableScroll(){
    val params = getLayoutParams() as AppBarLayout.LayoutParams
    params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL + AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS;
}

fun Toolbar.disableScroll(){
    val params = getLayoutParams() as AppBarLayout.LayoutParams
    params.scrollFlags = 0;
}