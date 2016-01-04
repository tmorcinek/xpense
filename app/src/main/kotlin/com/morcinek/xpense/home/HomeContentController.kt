package com.morcinek.xpense.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.morcinek.xpense.R

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class HomeContentController(private val activity: FragmentActivity) {

    fun addFragment(fragment: Fragment, tag: String) {
        addFragment(fragment, false, tag)
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean = false, tag: String = fragment.javaClass.name) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commit()
    }
}
