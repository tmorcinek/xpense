package com.morcinek.xpense.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.morcinek.xpense.Application
import com.morcinek.xpense.R

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        (application as Application).component.inject(this)
    }
}
