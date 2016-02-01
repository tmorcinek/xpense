package com.morcinek.xpense.home.config.fragments

import android.os.Bundle
import android.preference.PreferenceFragment
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.betterpickers.setTitle

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class SettingsFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setTitle(R.string.settings_label)
    }
}
