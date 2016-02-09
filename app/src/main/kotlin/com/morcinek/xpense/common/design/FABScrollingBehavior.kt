package com.morcinek.xpense.common.design

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.View
import com.morcinek.xpense.R
import org.jetbrains.anko.dimen

class FABScrollingBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<FloatingActionButton>(context, attrs) {

    private val toolbarHeight: Int

    init {
        this.toolbarHeight = context.dimen(R.dimen.abc_action_bar_default_height_material)
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, fab: FloatingActionButton, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, fab: FloatingActionButton, dependency: View): Boolean {
        if (layoutDependsOn(parent, fab, dependency)) {
            val distanceToScroll = fab.getHeight()
            val ratio = dependency.getY() / toolbarHeight.toFloat()
            fab.setTranslationY(-distanceToScroll * ratio)
        }
        return true
    }
}