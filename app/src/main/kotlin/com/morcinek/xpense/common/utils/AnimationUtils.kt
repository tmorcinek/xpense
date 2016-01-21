package com.morcinek.xpense.common.utils

import android.view.View
import com.nineoldandroids.animation.ObjectAnimator

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun View.startWarningAnimation() {
    val objectAnimator = ObjectAnimator.ofFloat(this, "translationX", 0f, 0f, -30f, 0f, -15f, 0f, 0f)
    objectAnimator.setDuration(500)
    objectAnimator.start()
}
