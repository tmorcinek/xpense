package com.morcinek.xpense.common.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Context.showSnackbar(view: View, text: Int, length: Int = Snackbar.LENGTH_LONG){
    Snackbar.make(view, text, length).show()
}
