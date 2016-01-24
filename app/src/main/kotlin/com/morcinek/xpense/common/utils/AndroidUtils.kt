package com.morcinek.xpense.common.utils

import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Activity.finishOk(){
    setResult(AppCompatActivity.RESULT_OK)
    finish()
}

fun Window.showSoftInput() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}

fun Window.hideSoftInput() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
}

fun View.setDrawableColor(color: Int) {
    (background as GradientDrawable).setColor(color)
}

fun EditText.getTrimString() = getText().toString().trim()

fun EditText.setTextWithSelection(text: CharSequence) {
    setText(text)
    setSelection(text.length)
}
