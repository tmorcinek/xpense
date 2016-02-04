package com.morcinek.xpense.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.morcinek.xpense.R

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class HomeContentController(private val activity: FragmentActivity) {

    val fragmentManager by lazy { activity.supportFragmentManager }

    val currentFragment: Fragment
        get() {
            val fragments = fragmentManager.getFragments()
            return fragments.get(fragmentManager.getBackStackEntryCount())
        }

    fun switchFragment(fragment: Fragment, addToBackStack: Boolean = false, tag: String = fragment.javaClass.name) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commit()
    }
}
