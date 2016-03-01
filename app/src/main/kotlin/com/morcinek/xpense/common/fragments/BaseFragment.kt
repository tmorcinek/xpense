package com.morcinek.xpense.common.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.morcinek.xpense.R
import kotlinx.android.synthetic.main.home_content.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
abstract class BaseFragment : Fragment() {

    protected abstract fun getLayoutResourceId(): Int

    open protected val menuResourceId: Int = R.menu.empty

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(menuResourceId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun registerFAB(onClickListener: View.OnClickListener) {
        activity.fab.setOnClickListener(onClickListener)
    }

    fun unregisterFAB() {
        activity.fab.setOnClickListener(activity as View.OnClickListener)
    }
}