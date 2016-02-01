package com.morcinek.xpense.home.config.fragments

import android.os.Bundle
import android.preference.PreferenceFragment
import com.morcinek.xpense.BuildConfig
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.betterpickers.setTitle

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class AboutFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about);
        setTitle(R.string.about_label)

        findPreference(getString(R.string.version_preference_key)).summary = BuildConfig.VERSION_NAME
    }
}
