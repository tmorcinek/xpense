package com.morcinek.xpense.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.helpers.PreferencesHelper
import com.morcinek.xpense.common.utils.startActivityForResult
import com.morcinek.xpense.home.HomeActivity
import com.morcinek.xpense.project.ProjectActivity
import com.morcinek.xpense.splash.data.Initializer
import org.jetbrains.anko.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    @field:[Inject Named("initializers")]
    lateinit var initializers: Array<Initializer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        (application as Application).component.inject(this)

        async() {
            initializeDatabase()
            uiThread {
                setupNextActivity()
            }
        }
    }

    private fun initializeDatabase() {
        if (!preferencesHelper.isDatabaseInitialized()) {
            initializers.forEach {
                it.initialize()
            }
            preferencesHelper.setDatabaseInitialized()
        }
    }

    private fun setupNextActivity() {
        if (preferencesHelper.hasCurrentProject()) {
            startActivity<HomeActivity>()
            finish()
        } else {
            startActivityForResult<ProjectActivity>()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> startActivity<HomeActivity>()
        }
        finish()
    }
}
