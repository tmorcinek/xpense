package com.morcinek.xpense.home.config

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.getSerializableExtra
import kotlinx.android.synthetic.main.preferences.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PreferencesActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_outside_right)
        setContentView(R.layout.preferences)
        setupToolbar()
        if (savedInstanceState == null) {
            val classType = intent.getSerializableExtra<Class<Fragment>>()!!
            fragmentManager.beginTransaction().replace(R.id.content, classType.newInstance()).commit();
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_inside_left, R.anim.slide_out_right)
    }
}
