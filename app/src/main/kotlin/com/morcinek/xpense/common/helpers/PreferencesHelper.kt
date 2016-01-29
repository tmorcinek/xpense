package com.morcinek.xpense.common.helpers

import android.content.SharedPreferences
import com.morcinek.xpense.common.utils.getLong
import com.morcinek.xpense.common.utils.putBoolean
import com.morcinek.xpense.common.utils.putLong

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PreferencesHelper(private val sharedPreferences: SharedPreferences) {

    private val CURRENT_PROJECT = "currentProject"
    private val DATABASE_INITIALIZED = "databaseInitialize"

    fun hasCurrentProject() = sharedPreferences.contains(CURRENT_PROJECT)
    fun getCurrentProjectId() = sharedPreferences.getLong(CURRENT_PROJECT)
    fun putCurrentProjectId(id: Long) = sharedPreferences.putLong(CURRENT_PROJECT, id)

    fun isDatabaseInitialized() = sharedPreferences.getBoolean(DATABASE_INITIALIZED, false)
    fun setDatabaseInitialized() = sharedPreferences.putBoolean(DATABASE_INITIALIZED, true)
}