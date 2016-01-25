package com.morcinek.xpense.common.helpers

import android.content.SharedPreferences
import com.morcinek.xpense.common.utils.getString

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PreferencesHelper(private val sharedPreferences: SharedPreferences) {

    fun getCurrentProjectName() = sharedPreferences.getString("currentProject")
}