package com.morcinek.xpense.common.helpers

import android.content.SharedPreferences
import com.morcinek.xpense.common.utils.getLong
import com.morcinek.xpense.common.utils.putLong

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PreferencesHelper(private val sharedPreferences: SharedPreferences) {

    private val CURRENT_PROJECT = "currentProject"

    fun getCurrentProjectId() = sharedPreferences.getLong(CURRENT_PROJECT)
    fun putCurrentProjectId(id: Long) = sharedPreferences.putLong(CURRENT_PROJECT, id)
}