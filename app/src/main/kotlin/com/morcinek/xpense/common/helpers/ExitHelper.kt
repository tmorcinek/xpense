package com.morcinek.xpense.common.helpers

import android.content.Context
import android.widget.Toast
import com.morcinek.xpense.R

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExitHelper(val context: Context) {

    private val toast by lazy {
        Toast.makeText(context, R.string.app_exit_message, Toast.LENGTH_SHORT)
    }

    fun reset() {
        toast.cancel()
    }

    fun canExit(): Boolean {
        if (toast.view.isShown) {
            toast.cancel()
            return true
        } else {
            toast.show()
            return false
        }
    }
}