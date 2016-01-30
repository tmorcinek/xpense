package com.morcinek.xpense.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.morcinek.xpense.Application
import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.startActivityFromFragment
import com.morcinek.xpense.expense.ExpenseActivity
import com.morcinek.xpense.home.history.HistoryFragment
import com.morcinek.xpense.home.navigation.NavigationExpenseManager
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.home_content.*
import kotlinx.android.synthetic.main.navigation_header.view.*
import javax.inject.Inject

/**
 * Copyright 2015 Tomasz Morcinek. All rights reserved.
 */
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var navigationExpenseManager: NavigationExpenseManager

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
            startActivityFromFragment<ExpenseActivity>(homeContentController.currentFragment)
        }
    }

    private fun setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this)
        val headerView = navigationView.getHeaderView(0)
        headerView.title.text = navigationExpenseManager.title
        headerView.subtitle.text = navigationExpenseManager.subtitle
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
            R.id.settings -> TODO()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
