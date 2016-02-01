package com.morcinek.xpense.home.config.fragments

import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import com.morcinek.xpense.R

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class AboutFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about);

        (activity as AppCompatActivity).supportActionBar.setTitle(R.string.about_label)
    }
}
