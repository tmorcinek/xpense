package com.morcinek.xpense.common.helpers

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ExecutionHelper(val success: () -> Unit, val warning: () -> Unit, val timePeriod: Long = 2500) {

    private var time : Long? = null

    fun reset(){
        time = null
    }

    fun execute(){
        var currentTime = System.currentTimeMillis()
        if (time == null || currentTime - time as Long > timePeriod) {
            time = currentTime
            warning()
        } else {
            success()
        }
    }
}