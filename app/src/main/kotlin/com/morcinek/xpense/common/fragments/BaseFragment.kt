package com.morcinek.xpense.common.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.home_content.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class BaseFragment : Fragment() {

    protected abstract fun getLayoutResourceId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    fun registerFAB(onClickListener: View.OnClickListener) {
        activity.fab.setOnClickListener(onClickListener)
    }

    fun unregisterFAB() {
        activity.fab.setOnClickListener(activity as View.OnClickListener)
    }
}