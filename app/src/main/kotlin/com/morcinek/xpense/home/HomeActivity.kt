package com.morcinek.xpense.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.startActivity
import com.morcinek.xpense.common.utils.startActivityForResult
import com.morcinek.xpense.data.project.ProjectManager
import com.morcinek.xpense.expense.ExpenseActivity
import com.morcinek.xpense.home.history.HistoryFragment
import com.morcinek.xpense.project.ProjectActivity
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.home_content.*
import javax.inject.Inject

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val homeContentController: HomeContentController = HomeContentController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        (application as Application).component.inject(this)

        setupToolbar()
        setupToggle()
        setupActionButton()
        setupNavigationView()
        setupFragment(savedInstanceState)
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            homeContentController.addFragment(HistoryFragment())
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupToggle() {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupActionButton() {
        fab.setOnClickListener {
            startActivity<ExpenseActivity>()
        }
    }

    private fun setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_gallery -> TODO()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
