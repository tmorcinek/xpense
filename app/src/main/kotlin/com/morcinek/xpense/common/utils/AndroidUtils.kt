package com.morcinek.xpense.common.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.morcinek.xpense.R
import kotlinx.android.synthetic.main.overview.*
import org.jetbrains.anko.dimen

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Activity.finishOk() {
    setResult(AppCompatActivity.RESULT_OK, getIntent())
    finish()
}

fun Window.showSoftInput() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}

fun Window.hideSoftInput() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
}

fun Activity.hideSoftInput(view: View) {
    getInputMethodManager().hideSoftInputFromWindow(view.windowToken, 0)
}

private fun Activity.getInputMethodManager() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun View.setDrawableColor(color: Int) {
    (background as GradientDrawable).setColor(color)
}

fun View.setTopMargin(marginResource: Int) {
    val layoutParams = layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.topMargin = context.dimen(marginResource)
}

fun View.removeTopMargin() {
    val layoutParams = layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.topMargin = 0
}

fun View.setLayoutHeight(height: Int) {
    layoutParams.height = height
}

fun EditText.getTrimString() = getText().toString().trim()

fun EditText.setTextWithSelection(text: CharSequence) {
    setText(text)
    setSelection(text.length)
}

fun DialogFragment.show(fragmentManager: FragmentManager) {
    show(fragmentManager, javaClass.name)
}

fun Activity.setBackgroundColor(color: Int){
    window.decorView.setBackgroundColor(color)
}

fun Activity.setStatusBarColor(color: Int){
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
        window.statusBarColor = color
    }
}
