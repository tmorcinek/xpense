package com.morcinek.xpense.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.startActivityForResult
import com.morcinek.xpense.data.project.ProjectManager
import com.morcinek.xpense.home.HomeActivity
import com.morcinek.xpense.project.ProjectActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var projectManager: ProjectManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        (application as Application).component.inject(this)

        setupNextActivity()
    }

    private fun setupNextActivity() {
        if (projectManager.currentProject == null) {
            startActivityForResult<ProjectActivity>()
        } else {
            startActivity<HomeActivity>()
            finish()
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
